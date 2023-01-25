package com.cometproject.server.network.messages.outgoing.user.profile;

import com.cometproject.api.game.players.data.PlayerAvatar;
import com.cometproject.api.game.players.data.components.PlayerRelationships;
import com.cometproject.api.game.players.data.components.messenger.RelationshipLevel;
import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.server.game.players.PlayerManager;
import com.cometproject.server.protocol.headers.Composers;
import com.cometproject.server.protocol.messages.MessageComposer;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class RelationshipsMessageComposer extends MessageComposer {
    private final int playerId;
    private final Map<Integer, RelationshipLevel> relationships;

    public RelationshipsMessageComposer(final int playerId, final Map<Integer, RelationshipLevel> relationships) {
        this.playerId = playerId;
        this.relationships = relationships;
    }

    public RelationshipsMessageComposer(final int playerId) {
        this.playerId = playerId;
        this.relationships = null;
    }

    @Override
    public short getId() {
        return Composers.GetRelationshipsMessageComposer;
    }

    @Override
    public void compose(IComposer msg) {
        msg.writeInt(playerId);

        if (relationships == null || relationships.size() == 0) {
            msg.writeInt(0);
            return;
        }

        msg.writeInt(relationships.size());

        int hearts = PlayerRelationships.countByLevel(RelationshipLevel.HEART, relationships);
        int smiles = PlayerRelationships.countByLevel(RelationshipLevel.SMILE, relationships);
        int bobbas = PlayerRelationships.countByLevel(RelationshipLevel.BOBBA, relationships);

        List<Integer> relationshipKeys = Lists.newArrayList(relationships.keySet());
        Collections.shuffle(relationshipKeys);

        for (Integer relationshipKey : relationshipKeys) {
            RelationshipLevel level = relationships.get(relationshipKey);

            PlayerAvatar data = PlayerManager.getInstance().getAvatarByPlayerId(relationshipKey, PlayerAvatar.USERNAME_FIGURE);

            if (data == null) {
                msg.writeInt(0);
                msg.writeInt(0);
                msg.writeInt(0); // id
                msg.writeString("Username");
                msg.writeString("hr-115-42.hd-190-1.ch-215-62.lg-285-91.sh-290-62");
            } else {
                msg.writeInt(level.getLevelId());
                msg.writeInt(level == RelationshipLevel.HEART ? hearts : level == RelationshipLevel.SMILE ? smiles : bobbas);
                msg.writeInt(data.getId());
                msg.writeString(data.getUsername());
                msg.writeString(data.getFigure());
            }

        }
    }
}
