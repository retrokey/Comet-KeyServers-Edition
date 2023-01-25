package com.cometproject.server.protocol.crypto.exceptions;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

import com.cometproject.server.protocol.crypto.exceptions.utils.BigIntegerUtils;
import com.cometproject.server.protocol.crypto.exceptions.utils.HexUtils;

public class HabboDiffieHellman {

    private static final int DH_PRIMES_BIT_SIZE = 128;
    private static final int DH_KEY_BIT_SIZE = 128;

    private final HabboRSACrypto crypto;

    private BigInteger DHPrime;
    private BigInteger DHGenerator;
    private BigInteger DHPrivate;
    private BigInteger DHPublic;

    public HabboDiffieHellman(HabboRSACrypto crypto) {
        this.crypto = crypto;
        this.generateDHPrimes();
        this.generateDHKeys();
    }

    public BigInteger getDHPrime() {
        return DHPrime;
    }

    public BigInteger getDHGenerator() {
        return DHGenerator;
    }

    private void generateDHPrimes() {
        this.DHPrime = BigInteger.probablePrime(DH_PRIMES_BIT_SIZE, ThreadLocalRandom.current());
        this.DHGenerator = BigInteger.probablePrime(DH_PRIMES_BIT_SIZE, ThreadLocalRandom.current());

        if (this.DHGenerator.compareTo(this.DHPrime) > 0) {
            BigInteger temp = this.DHPrime;

            this.DHPrime = this.DHGenerator;
            this.DHGenerator = temp;
        }
    }

    private void generateDHKeys() {
        this.DHPrivate = BigInteger.probablePrime(DH_KEY_BIT_SIZE, ThreadLocalRandom.current());
        this.DHPublic = this.DHGenerator.modPow(this.DHPrivate, this.DHPrime);
    }

    private String encryptBigInteger(BigInteger integer) throws HabboCryptoException {
        String str = integer.toString(10);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = this.crypto.Sign(bytes);

        return HexUtils.toHex(encrypted).toLowerCase();
    }

    private BigInteger decryptBigInteger(String str) throws HabboCryptoException {
        byte[] bytes = HexUtils.toBytes(str);
        byte[] decrypted = this.crypto.Decrypt(bytes);
        String intStr = new String(decrypted, StandardCharsets.UTF_8);

        return new BigInteger(intStr, 10);
    }

    public String getPublicKey() throws HabboCryptoException {
        return encryptBigInteger(this.DHPublic);
    }

    public String getSignedPrime() throws HabboCryptoException {
        return encryptBigInteger(this.DHPrime);
    }

    public String getSignedGenerator() throws HabboCryptoException {
        return encryptBigInteger(this.DHGenerator);
    }

    public void doHandshake(String signedPrime, String signedGenerator) throws HabboCryptoException {
        this.DHPrime = decryptBigInteger(signedPrime);
        this.DHGenerator = decryptBigInteger(signedGenerator);

        if (this.DHPrime == null || this.DHGenerator == null) {
            throw new HabboCryptoException("DHPrime or DHGenerator was null.");
        }

        if (this.DHPrime.compareTo(BigInteger.valueOf(2)) < 1) {
            throw new HabboCryptoException("Prime cannot be <= 2!\nPrime: " + this.DHPrime.toString());
        }

        if (this.DHGenerator.compareTo(this.DHPrime) > -1) {
            throw new HabboCryptoException("Generator cannot be >= Prime!\nPrime: " + this.DHPrime.toString() + "\nGenerator: " + this.DHGenerator.toString());
        }

        generateDHKeys();
    }

    public byte[] getSharedKey(String publicKeyStr) throws HabboCryptoException {
        BigInteger publicKey = this.decryptBigInteger(publicKeyStr);
        BigInteger sharedKey = publicKey.modPow(this.DHPrivate, this.DHPrime);

        return BigIntegerUtils.toUnsignedByteArray(sharedKey);
    }

}