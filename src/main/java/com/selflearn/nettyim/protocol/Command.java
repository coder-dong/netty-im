package com.selflearn.nettyim.protocol;

/**
 * Created by coding-dong on 2018/11/1.
 */
public enum Command {
    LOGIN_REQUEST_COMMAND((byte) 1),

    LOGIN_RESPONSE_COMMAND((byte) 2),

    MESSAGE_REQUEST_COMMAND((byte) 3),

    MESSAGE_RESPONSE_COMMAND((byte) 4),

    CREATE_GROUP_REQUEST_COMMAND((byte) 5),

    CREATE_GROUP_RESPONSE_COMMAND((byte) 6),

    JOIN_GROUP_REQUEST_COMMAND((byte) 7),

    JOIN_GROUP_RESPONSE_COMMAND((byte) 8),

    QUIT_GROUP_REQUEST_COMMAND((byte) 9),

    QUIT_GROUP_RESPONSE_COMMAND((byte) 10),

    LIST_GROUP_REQUEST_COMMAND((byte) 11),

    LIST_GROUP_RESPONSE_COMMAND((byte) 12),

    MESSAGE_GROUP_REQUEST_COMMAND((byte) 13),

    MESSAGE_GROUP_RESPONSE_COMMAND((byte) 14),
    ;

    private byte type;

    Command(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }
}
