package com.selflearn.nettyim.util;

import com.selflearn.nettyim.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * Created by coding-dong on 2018/11/4.
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static void markAsLogout(Channel channel){
        channel.attr(Attributes.LOGIN).set(null);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> attribute = channel.attr(Attributes.LOGIN);

        return attribute.get() != null;
    }

}
