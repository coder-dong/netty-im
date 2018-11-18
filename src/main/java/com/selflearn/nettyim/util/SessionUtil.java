package com.selflearn.nettyim.util;

import com.selflearn.nettyim.attribute.Attributes;
import com.selflearn.nettyim.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by coding-dong on 2018/11/12.
 */
public class SessionUtil {

    private static Map<Long, Channel> channelMap = new ConcurrentHashMap<>(8);

    private static Map<Long, DefaultChannelGroup> groupMap = new ConcurrentHashMap<>(8);

    public static void bindSession(Session session, Channel channel){
        channelMap.putIfAbsent(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel){
        if (hasLogin(channel)){
            channelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){
        return channel.attr(Attributes.SESSION).get() != null;
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(long userId){
        return channelMap.get(userId);
    }

    public static void bindGroup(long groupId, DefaultChannelGroup channelGroup){
        groupMap.put(groupId, channelGroup);
    }

    public static boolean addGroupMember(long groupId, Channel channel){
        DefaultChannelGroup channelGroup = groupMap.get(groupId);
        if (channelGroup != null){
            channelGroup.add(channel);
            return true;
        }else {
            return false;
        }
    }

    public static boolean removeGroupMember(long groupId, Channel channel){
        DefaultChannelGroup channelGroup = groupMap.get(groupId);
        if (channelGroup != null){
            channelGroup.remove(channel);
            return true;
        }else {
            return false;
        }
    }

    public static void unBindGroup(long groupId){
        groupMap.remove(groupId);
    }

    public static DefaultChannelGroup getGroup(long groupId){
        return groupMap.get(groupId);
    }
}
