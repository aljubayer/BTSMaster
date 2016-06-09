package com.OMCB.Model;

/**
 * Created by atchowdhury on 11/1/2015.
 */
public class GetBcsuLogical {

    public void GetBcsuAddress(){

        try{

            AutomatedTelnetClient telnet = new AutomatedTelnetClient(
                    "10.8.11.41", "ATIQUE", "ATIQUE123");
            //TimeUnit.SECONDS.sleep(2);
            System.out.println("Got Connection...");
//        telnet.sendCommand("ZNEL;");
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("run command");
            telnet.sendCommandSpecial("ZDDS;", ">");
            telnet.sendCommandSpecial("ZLE:U,RCBUGGGX",">");
            telnet.sendCommandSpecial("U", ">");
            String x=telnet.sendCommandSpecial("ZUSI:BCSU", ">");
            telnet.sendCommandSpecial("Z", ">");
            telnet.sendCommandSpecial("Z", ">");
            telnet.sendCommand("ZE");
            // TimeUnit.SECONDS.sleep(2);
            System.out.println("run command 2");
            telnet.disconnect();
            //System.out.println(x+"Value printed");
            String lines[] = x.split("\\r?\\n");
            for(String aLine:lines) {
                if (aLine.contains("BCSU-")) {

                    String[] aLineComponents=aLine.trim().split("\\r?\\n?\\s");
                    // aLine.replaceAll("\\t+","//");
                    for(String aComponent:aLineComponents){
                        if(!aComponent.trim().isEmpty()) {
                            System.out.println(aComponent);
                        }
                    }
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }


    }
}
