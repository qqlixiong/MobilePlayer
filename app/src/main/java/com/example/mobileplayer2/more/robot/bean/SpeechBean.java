package com.example.mobileplayer2.more.robot.bean;

import java.util.List;

public class SpeechBean {

    public String bg;
    public String ed;
    public String ls;
    public String sn;
    public List<WS> ws;

    public class WS {

        public String bg;
        public List<CW> cw;

    }

    public class CW {

        public String sc;
        public String w;
    }
}
