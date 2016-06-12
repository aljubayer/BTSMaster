package com.OMCB.Model;

public class GetBcsuLogical {

    public void GetBcsuAddress() {

        try {

            AutomatedTelnetClient telnet = new AutomatedTelnetClient(
                    "10.8.11.41", "ATIQUE", "ATIQUE123");

            System.out.println("Got Connection...");

            telnet.sendCommandSpecial("ZDDS;", ">");
            telnet.sendCommandSpecial("ZLE:U,RCBUGGGX", ">");
            telnet.sendCommandSpecial("U", ">");
            String x = telnet.sendCommandSpecial("ZUSI:BCSU", ">");
            telnet.sendCommandSpecial("Z", ">");
            telnet.sendCommandSpecial("Z", ">");
            telnet.sendCommand("ZE");

            System.out.println("run command 2");
            telnet.disconnect();

            String lines[] = x.split("\\r?\\n");
            for (String aLine : lines) {
                if (aLine.contains("BCSU-")) {

                    String[] aLineComponents = aLine.trim().split("\\r?\\n?\\s");

                    for (String aComponent : aLineComponents) {
                        if (!aComponent.trim().isEmpty()) {
                            System.out.println(aComponent);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
