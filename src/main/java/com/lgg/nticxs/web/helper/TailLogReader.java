package com.lgg.nticxs.web.helper;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.lgg.nticxs.web.utils.WSLogger;

/**
 * Created by movasim on 22/11/16.
 */
public class TailLogReader {

    String fileName = "";
    File file = null;
    
    private static WSLogger logger = new WSLogger();
    
    public TailLogReader(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public static void main(String[] args) throws IOException {
        TailLogReader reader= new TailLogReader("/var/log/movasim/leota/LeOTA_securePackage.log");
        String lines = null;

        long startPosition = reader.findEnd();
        long endPosition = reader.findEnd();
        if (endPosition < startPosition) {
            // File restarted
            startPosition = 0;
        }
        if (endPosition > startPosition) {
            lines = reader.readRegion(startPosition,endPosition);
            
            logger.logger("INFO", "SM-WEB", "", "", "", "main()", "", "", "", lines);
            
            startPosition = endPosition;
        }
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            endPosition = reader.findEnd();
            if (endPosition < startPosition) {
                // File restarted
                startPosition = 0;
            }
            if (endPosition > startPosition) {
                lines = reader.readRegion(startPosition,endPosition);
                
                logger.logger("INFO", "SM-WEB", "", "", "", "main()", "", "", "", lines);
                
                startPosition = endPosition;
            }
        }

    }

    public String readRegion(long startPosition, long endPosition) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(this.file, "r");
        byte[] buff = new byte[(int) (endPosition-startPosition)];
        raf.seek(startPosition);
        raf.read(buff);
        String result = new String(cleanUnprintableBytes(buff), "US-ASCII");
        raf.close();
        return result;
    }

    public long findEnd() {
        return this.file.length();
    }

    public String webalizeEndlines(String str) {
        return str.replaceAll("(\r\n|\n)", "<br />");
    }



    //agregado desde el Utils
    public static byte[] cleanUnprintableBytes(byte[] buffIn) {
        byte[] result = new byte[buffIn.length];
        for (int i  = 0; i < buffIn.length; i++) {
            result[i] = cleanUnprintableByte(buffIn[i]);
        }
        return result;
    }

    private static byte cleanUnprintableByte(byte b) {
        if (b == 10 || b == 13)
            return b;
        if (b < ' ' || b > '}') {
            return '.';
        }
        return b;
    }
}
