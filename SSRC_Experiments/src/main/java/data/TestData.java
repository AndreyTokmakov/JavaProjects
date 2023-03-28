package data;

import org.apache.commons.lang3.NotImplementedException;

public final class TestData
{
    public static final String clientMAC = "bc:6e:e2:03:74:ba";
    public static final String clientWiFiInterface = "wlp0s20f3";

    public static final String monitorWiFiInterface = "wlp0s20f3mon";

    public static final String cslWiFiInterface = "wlan1";
    public static final String cslWiFiMac = "e4:5f:01:61:60:27";
    public static final String cslWiFiAPName = "comms_sleeve#6027";
    public static final String cslWiFiAPPassword = "ssrcdemo";

    private TestData() {
        throw new NotImplementedException();
    }
}