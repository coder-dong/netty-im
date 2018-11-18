package com.selflearn.nettyim.attribute;

import com.selflearn.nettyim.session.Session;
import io.netty.util.AttributeKey;

/**
 * Created by coding-dong on 2018/11/2.
 */
public class Attributes {

    public static AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("util");

    public static AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
