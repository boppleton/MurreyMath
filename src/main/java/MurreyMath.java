import java.util.HashMap;

public class MurreyMath {

    public static HashMap<Integer, Double> getLines(double upper, double lower) {

        double upperPrice = upper;
        double lowerPrice = lower;
//        System.out.println("upper: " + upper + " lower: " + lower);

        //step 1: Price Range
        //
        double priceRange = upperPrice-lowerPrice;
//        System.out.println("price range: " + priceRange);

        //step 2: Scale Factor
        //
        double scaleFactor = 0;

        if (upperPrice > 25000) {
            scaleFactor = 100000;
        } else if (upperPrice > 2500 ) {
            scaleFactor = 10000;
        } else if (upperPrice > 250) {
            scaleFactor = 1000;
        } else if (upperPrice > 25) {
            scaleFactor = 100;
        } else {
            scaleFactor = 12.5;
        }
//        System.out.println("scalefactor: " + scaleFactor);

        //step 3: determine MMI that the square in time will be built from
        //
        double rangeMMI = 0;
        int octaveCount = 0;
        double scaleInterval = 0;

        double MMML_octave1 = scaleFactor/(Math.pow(8, 1));
        double MMML_rangeMMI = priceRange / MMML_octave1;
//        System.out.println("octave 1 - MMML: " + MMML_octave1 + " - " + MMML_rangeMMI);

        rangeMMI = MMML_rangeMMI;
        octaveCount = 1;
        scaleInterval = MMML_octave1;

        double mMML_octave2 = scaleFactor/(Math.pow(8, 2));
        double mMML_rangeMMI = priceRange / mMML_octave2;
//        System.out.println("octave 2 - mMML: " + mMML_octave2 + " - " + mMML_rangeMMI);

        if (rangeMMI < 1.25) {
            rangeMMI = mMML_rangeMMI;
            octaveCount = 2;
            scaleInterval = mMML_octave2;
        }

        double bMML_octave3 = scaleFactor/(Math.pow(8, 3));
        double bMML_rangeMMI = priceRange / bMML_octave3;
//        System.out.println("octave 3 - bMML: " + bMML_octave3 + " - " + bMML_rangeMMI);

        if (rangeMMI < 1.25) {
            rangeMMI = bMML_rangeMMI;
            octaveCount = 3;
            scaleInterval = bMML_octave3;
        }

        double bMML1_octave4 = scaleFactor/(Math.pow(8, 4));
        double bMML1_rangeMMI = priceRange / bMML1_octave4;
//        System.out.println("octave 4 - bMML1: " + bMML1_octave4 + " - " + bMML1_rangeMMI);

        if (rangeMMI < 1.25) {
            rangeMMI = bMML1_rangeMMI;
            octaveCount = 4;
            scaleInterval = bMML1_octave4;
        }

        double bMML2_octave5 = scaleFactor/(Math.pow(8, 5));
        double bMML2_rangeMMI = priceRange / bMML2_octave5;
//        System.out.println("octave 5 - bMML2: " + bMML2_octave5 + " - " + bMML2_rangeMMI);

        if (rangeMMI < 1.25) {
            rangeMMI = bMML2_rangeMMI;
            octaveCount = 5;
            scaleInterval = bMML2_octave5;
        }

        double bMML3_octave6 = scaleFactor/(Math.pow(8, 6));
        double bMML3_rangeMMI = priceRange / bMML3_octave6;
//        System.out.println("octave 6 - bMMML3: " + bMML3_octave6 + " - " + bMML3_rangeMMI);

        if (rangeMMI < 1.25) {
            rangeMMI = bMML3_rangeMMI;
            octaveCount = 6;
            scaleInterval = bMML3_octave6;
        }
//        System.out.println("rangeMMI: " + rangeMMI);
//        System.out.println("octave count: " + octaveCount);

        //step 4: determine height of square in time
        //
        double heightOfSquare = 0;

        if (rangeMMI>1.25 && rangeMMI<3) {
            heightOfSquare = 2;
        }

        if(rangeMMI>=3 && rangeMMI<5) {
            heightOfSquare = 4;
        }

        if (rangeMMI>5) {
            heightOfSquare = 8;
        }
//        System.out.println("height of square: " + heightOfSquare);
//        System.out.println("scale interval: " + scaleInterval);

        //step 5: find bottom of square in time
        //
        double MMML_oc1 = lowerPrice-0;
        int MMML_oc1_2 = Double.valueOf(MMML_oc1/MMML_octave1).intValue();
        double MMML_oc1_3 = MMML_oc1_2 * MMML_octave1;
//        System.out.println("MMML (octave = 1): " + MMML_oc1 + " -- " + MMML_oc1_2 + " -- " + MMML_oc1_3);

        double mMML_oc2 = lowerPrice-MMML_oc1_3;
        int mMML_oc2_2 = Double.valueOf(mMML_oc2/mMML_octave2).intValue();
        double mMML_oc2_3 = MMML_oc1_3 + (mMML_oc2_2 * mMML_octave2);
//        System.out.println("mMML (octave = 2): " + mMML_oc2 + " -- " + mMML_oc2_2 + " -- " + mMML_oc2_3);

        double bMML_oc3 = lowerPrice-mMML_oc2_3;
        int bMML_oc3_2 = Double.valueOf(bMML_oc3/bMML_octave3).intValue();
        double bMML_oc3_3 = mMML_oc2_3 + (bMML_oc3_2 * bMML_octave3);
//        System.out.println("bMML (octave = 3): " + bMML_oc3 + " -- " + bMML_oc3_2 + " -- " + bMML_oc3_3);

        double bMML1_oc4 = lowerPrice-bMML_oc3_3;
        int bMML1_oc4_2 = Double.valueOf(bMML1_oc4/bMML1_octave4).intValue();
        double bMML1_oc4_3 = bMML_oc3_3 + (bMML1_oc4_2 * bMML1_octave4);
//        System.out.println("bMML1 (octave = 4): " + bMML1_oc4 + " -- " + bMML1_oc4_2 + " -- " + bMML1_oc4_3);

        double bMML2_oc5 = lowerPrice-bMML1_oc4_3;
        int bMML2_oc5_2 = Double.valueOf(bMML2_oc5/bMML2_octave5).intValue();
        double bMML2_oc5_3 = bMML1_oc4_3 + (bMML2_oc5_2 * bMML2_octave5);
//        System.out.println("bMML2 (octave = 5): " + bMML2_oc5 + " -- " + bMML2_oc5_2 + " -- " + bMML2_oc5_3);

        double bMML3_oc6 = lowerPrice-bMML2_oc5_3;
        int bMML3_oc6_2 = Double.valueOf(bMML3_oc6/bMML3_octave6).intValue();
        double bMML3_oc6_3 = bMML2_oc5_3 + (bMML3_oc6_2 * bMML3_octave6);
//        System.out.println("bMML3 (octave = 6): " + bMML3_oc6 + " -- " + bMML3_oc6_2 + " -- " + bMML3_oc6_3);

        double baseML = 0;
        double bottomOfSquare = 0;
        String octaveName = "";

        switch (octaveCount) {
            case 1:
                baseML = MMML_oc1_2 % 8;
                bottomOfSquare = MMML_oc1_3;
                octaveName = "MMML";
                break;
            case 2:
                baseML = mMML_oc2_2 % 8;
                bottomOfSquare = mMML_oc2_3;
                octaveName = "mMML";
                break;
            case 3:
                baseML = bMML_oc3_2 % 8;
                bottomOfSquare = bMML_oc3_3;
                octaveName = "bMML";
                break;
            case 4:
                baseML = bMML1_oc4_2 % 8;
                bottomOfSquare = bMML1_oc4_3;
                octaveName = "bMML1";
                break;
            case 5:
                baseML = bMML2_oc5_2 % 8;
                bottomOfSquare = bMML2_oc5_3;
                octaveName = "bMML2";
                break;
            case 6:
                baseML = bMML3_oc6_2 % 8;
                bottomOfSquare = bMML3_oc6_3;
                octaveName = "bMML3";
                break;
        }
//        System.out.println("base ML: " + baseML);
//        System.out.println("bottom of square in time: " + bottomOfSquare);
//        System.out.println("on the " + baseML + "/8th's " + octaveName +"'s");

        double frameHeight = heightOfSquare * scaleInterval;
//        System.out.println("frame height: " + frameHeight);

        //step 6: find best square
        //
        double bestsq2 = baseML;
        double bestsq1 = bestsq2-1;
        double bestsq3 = bestsq2+1;

        double bot2 = bottomOfSquare;
        double top2 = bot2 + (heightOfSquare * scaleInterval);
        double error2 = (Math.abs(upperPrice-top2) + Math.abs(lowerPrice-bot2));
//        System.out.println("row2: " + bestsq2 + " -- " + bot2 + " -- " + top2 + " -- " + error2);

        double bot1 = bot2 - scaleInterval;
        double top1 = top2 - scaleInterval;
        double error1 = (Math.abs(upperPrice-top1) + Math.abs(lowerPrice-bot1));
//        System.out.println("row1: " + bestsq1 + " -- " + bot1 + " -- " + top1 + " -- " + error1);

        double bot3 = bot2 + scaleInterval;
        double top3 = top2 + scaleInterval;
        double error3 = (Math.abs(upperPrice-top3) + Math.abs(lowerPrice-bot3));
//        System.out.println("row3: " + bestsq3 + " -- " + bot3 + " -- " + top3 + " -- " + error3);

        double rightside2_1 = Math.min(error1, Math.min(error2, error3));
//        System.out.println("2 rightside 1/5: " + rightside2_1);
        double rightside2_2 = error1==rightside2_1 ? 1:0;
//        System.out.println("2 rightside 2/5: " + rightside2_2);
        double rightside2_3 = error2==rightside2_1 ? 2:0;
//        System.out.println("2 rightside 3/5: " + rightside2_3);
        double rightside2_4 = error3==rightside2_1 ? 3:0;
//        System.out.println("2 rightside 4/5: " + rightside2_4);

        double rightside2_5 = Math.max(rightside2_2, Math.max(rightside2_3, rightside2_4));
//        System.out.println("2 rightside 5/5: " + rightside2_5);

        double botfinal2h;
        if (rightside2_5 == 1) {
            botfinal2h = bot1;
        } else if (rightside2_5 == 2) {
            botfinal2h = bot2;
        } else {
            botfinal2h = bot3;
        }

        double topfinal2h;
        if (rightside2_5 == 1) {
            topfinal2h = top1;
        } else if (rightside2_5 == 2) {
            topfinal2h = top2;
        } else {
            topfinal2h = top3;
        }
//        System.out.println("\n octave count 2 final - bot: " + botfinal2h + " top: " + topfinal2h + " end: " + rightside2_5 + "\n");

        //
        // height4

        // 0
        double bot4_0 = bottomOfSquare - (baseML*scaleInterval);
        double top4_0 = bot4_0 + (scaleInterval*4);
        double error4_0 = Math.abs(upperPrice-top4_0) + Math.abs(lowerPrice-bot4_0);

//        System.out.println("height4 row 0, bot4_0: " +bot4_0 + " top: " + top4_0 + " error: " + error4_0);

        // 2
        double bot4_2 = bot4_0 + (scaleInterval*2);
        double top4_2 = bot4_2 + (scaleInterval*4);
        double error4_2 = Math.abs(upperPrice-top4_2) + Math.abs(lowerPrice-bot4_2);

//        System.out.println("height4 row 2, bot4_2: " +bot4_2 + " top: " + top4_2 + " error: " + error4_2);

        // 4
        double bot4_4 = bot4_0 + (scaleInterval*4);
        double top4_4 = bot4_4 + (scaleInterval*4);
        double error4_4 = Math.abs(upperPrice-top4_4) + Math.abs(lowerPrice-bot4_4);

//        System.out.println("height4 row 4, bot4_4: " +bot4_4 + " top: " + top4_4 + " error: " + error4_4);

        // 6
        double bot4_6 = bot4_0 + (scaleInterval*6);
        double top4_6 = bot4_6 + (scaleInterval*4);
        double error4_6 = Math.abs(upperPrice-top4_6) + Math.abs(lowerPrice-bot4_6);

//        System.out.println("height4 row 6, bot4_6: " +bot4_6 + " top: " + top4_6 + " error: " + error4_6);

        // 01
        double bot4_01 = bot4_0 + (scaleInterval*0);
        double top4_01 = bot4_01 + (scaleInterval*4);
        double error4_01 = Math.abs(upperPrice-top4_01) + Math.abs(lowerPrice-bot4_01);

//        System.out.println("height4 row 01, bot4_01: " +bot4_01 + " top: " + top4_01 + " error: " + error4_01);

        double height4righttop = Math.min(error4_0, Math.min(error4_2, Math.min(error4_4, Math.min(error4_6, error4_01))));
//        System.out.println("\nheight4 rightside top: " + height4righttop);

        double height4octavebottomright = 0;

        double height4finalbot = 0;
        double height4finaltop = 0;

        if (height4righttop == error4_0) {
            height4octavebottomright = 1;
            height4finalbot = bot4_0;
            height4finaltop = top4_0;
        } else if (height4righttop == error4_2) {
            height4octavebottomright = 2;
            height4finalbot = bot4_2;
            height4finaltop = top4_2;
        } else if (height4righttop == error4_4) {
            height4octavebottomright = 3;
            height4finalbot = bot4_4;
            height4finaltop = top4_4;
        } else if (height4righttop == error4_6) {
            height4octavebottomright = 4;
            height4finalbot = bot4_6;
            height4finaltop = top4_6;
        } else if (height4righttop == error4_01) {
            height4octavebottomright = 5;
            height4finalbot = bot4_01;
            height4finaltop = top4_01;
        }
//        System.out.println("octave count 4 final - bot: " + height4finalbot + " top: " + height4finaltop + " bottomright: " + height4octavebottomright );


        double bot8_0 = bottomOfSquare - (baseML * scaleInterval);
        double top8_0 = bot8_0 + (heightOfSquare * scaleInterval);
        double error8_0 = Math.abs(upperPrice - top8_0) + Math.abs(lowerPrice - bot8_0);
//        System.out.println("height8 row 0 bot: " + bot8_0 + " top: " + top8_0 + " error: " + error8_0);

        double bot8_4 = bot8_0 + (scaleInterval * 4);
        double top8_4 = bot8_4 + (heightOfSquare * scaleInterval);
        double error8_4 = Math.abs(upperPrice - top8_4) + Math.abs(lowerPrice - bot8_4);
//        System.out.println("height8 row 4 bot: " + bot8_4 + " top: " + top8_4 + " error: " + error8_4);


        double bot8_01 = bot8_0 + (scaleInterval * 8);
        double top8_01 = bot8_01 + (heightOfSquare * scaleInterval);
        double error8_01 = Math.abs(upperPrice - top8_01) + Math.abs(lowerPrice - bot8_01);
//        System.out.println("height8 row 01 bot: " + bot8_01 + " top: " + top8_01 + " error: " + error8_01);


        double height8righttop = Math.min(error8_0, Math.min(error8_4, error8_01));
//        System.out.println("\nheight8 rightside top: " + height8righttop);

        double height8bottomright = 0;

        double height8finalbot = 0;
        double height8finaltop = 0;

        if (height8righttop == error8_0) {
            height8bottomright = 1;
            height8finalbot = bot8_0;
            height8finaltop = top8_0;
        } else if (height8righttop == error8_4) {
            height8bottomright = 2;
            height8finalbot = bot8_4;
            height8finaltop = top8_4;
        } else if (height8righttop == error8_01) {
            height8bottomright = 3;
            height8finalbot = bot8_01;
            height8finaltop = top8_01;
        }
//        System.out.println("octave count 8 final - bot: " + height8finalbot + " top: " + height8finaltop + " bottomright: " + height8bottomright );


        double finalbot = 0;

        if (heightOfSquare == 2) {
            finalbot = botfinal2h;
        } else if (heightOfSquare == 4) {
            finalbot = height4finalbot;
        } else {
            finalbot = height8finalbot;
        }

        double finaltop = 0;

        if (heightOfSquare == 2) {
            finaltop = topfinal2h;
        } else if (heightOfSquare == 4) {
            finaltop = height4finaltop;
        } else {
            finaltop = height8finaltop;
        }

        double lineheight18 = (finaltop-finalbot)/8;

        double bestSqPlus28th = finaltop + (2 * lineheight18);
//        System.out.println("sqintime: " + heightOfSquare + " 1/8 line height: " + lineheight18 + " final bot: " + finalbot + " final top: " + finaltop + " +2/8th: " + bestSqPlus28th);


        boolean pricehighover28bool = upperPrice > bestSqPlus28th;

        double sqintime = 0;

        if (pricehighover28bool) {
            sqintime = 4;
        } else {
            sqintime = heightOfSquare;
        }


        double dubcheckFinalBot = 0;

        if (sqintime == 2) {
            dubcheckFinalBot = botfinal2h;
        } else if (sqintime == 4) {
            dubcheckFinalBot = height4finalbot;
        } else {
            dubcheckFinalBot = height8finalbot;
        }

        double dubcheckFinalTop = 0;

        if (sqintime == 2) {
            dubcheckFinalTop = topfinal2h;
        } else if (sqintime == 4) {
            dubcheckFinalTop = height4finaltop;
        } else {
            dubcheckFinalTop = height8finaltop;
        }

        double dubchecklineheight = (dubcheckFinalTop - dubcheckFinalBot) / 8;

        double dubcheckPlus28th = dubcheckFinalTop + (2 * dubchecklineheight);

//        System.out.println("is price high > 2/8th line " + pricehighover28bool);

//        System.out.println("sqintime: " + sqintime + " dubcheckBot: " + dubcheckFinalBot + " dubcheckTop: " + dubcheckFinalTop + " dubcheck +2/8th: " + dubcheckPlus28th   );

//        System.out.println("dubcheck lineheight: " + dubchecklineheight);

//        System.out.println("\n\n");

        HashMap<Integer, Double> murreylines = new HashMap<>();

        murreylines.put(8, dubcheckFinalTop);

        murreylines.put(9, murreylines.get(8) + lineheight18);

        murreylines.put(10, murreylines.get(9) + lineheight18);

        murreylines.put(11, murreylines.get(10) + lineheight18);

        //new way
//        murreylines.put(7, murreylines.get(8) - lineheight18);
//        murreylines.put(6, murreylines.get(7) - lineheight18);
//        murreylines.put(5, murreylines.get(6) - lineheight18);
//        murreylines.put(4, murreylines.get(5) - lineheight18);
//        murreylines.put(3, murreylines.get(4) - lineheight18);
//        murreylines.put(2, murreylines.get(3) - lineheight18);
//        murreylines.put(1, murreylines.get(2) - lineheight18);
//        murreylines.put(0, murreylines.get(1) - lineheight18);
//        murreylines.put(-1, murreylines.get(0) - lineheight18);
//        murreylines.put(-2, murreylines.get(-1) - lineheight18);
//        murreylines.put(-3, murreylines.get(-2) - lineheight18);

// old way that gave that gap
        murreylines.put(0, dubcheckFinalBot);
        murreylines.put(-1, murreylines.get(0) - lineheight18);
        murreylines.put(-2, murreylines.get(-1) - lineheight18);
        murreylines.put(-3, murreylines.get(-2) - lineheight18);
        murreylines.put(1, murreylines.get(0) + lineheight18);
        murreylines.put(2, murreylines.get(1) + lineheight18);
        murreylines.put(3, murreylines.get(2) + lineheight18);
        murreylines.put(4, murreylines.get(3) + lineheight18);
        murreylines.put(5, murreylines.get(4) + lineheight18);
        murreylines.put(6, murreylines.get(5) + lineheight18);
        murreylines.put(7, murreylines.get(6) + lineheight18);

        for (int i = 11; i >= -3; i--) {
//            System.out.println("line " + i + "/8: " + murreylines.get(i));
        }

        return murreylines;
    }

    public static void main(String[] args) { }

}