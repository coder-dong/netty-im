package com.selflearn.nettyim.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by coding-dong on 2018/10/31.
 */
@Data
public abstract class Packet {

    @JSONField(deserialize = false, serialize = false)
    private byte version = 1;

    @JSONField(serialize = false)
    public abstract byte getCommand();
}
