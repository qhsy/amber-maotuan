package zz.mk.utilslibrary.walle;

import java.util.Map;

/**
 * author: zhu on 2017/7/10 15:35
 * email: mackkilled@gmail.com
 */

public class ChannelInfo {
    private final String channel;
    private final Map<String, String> extraInfo;

    public ChannelInfo(final String channel, final Map<String, String> extraInfo) {
        this.channel = channel;
        this.extraInfo = extraInfo;
    }

    public String getChannel() {
        return channel;
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }
}
