package com.lgg.nticxs.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.lgg.nticxs.web.model.Nota;

public class Utils {
	
	public static byte[] incByteArray(byte[] value) {
		byte[] result = new byte[value.length];
		boolean overflow = true;
		int currValue = 0;
		for (int i = value.length -1; i >= 0; i--) {
			currValue = value[i];
			if (overflow)
				currValue++;
			
			overflow = (currValue > 0x0ff);
			result[i] = (byte)(currValue & 0x0ff);
			
		}
		return result;
	}

	public static byte toByte(int s) {
        return (byte)(s & 0x00FF);
    }
	
	public static byte[] toBytes(short s) {
        return new byte[]{(byte)((s & 0xFF00)>>8),(byte)(s & 0x00FF)};
    }
	
	public static short bytesToShort(byte[] buffer, int i) {
        return (short) ((short)(((buffer[i] << 8) & 0x0FF00)) + (short)(buffer[i + 1] & 0x00FF));
    }

	public static String toHex(byte bt)
	{
		return "0x" + String.format("%02x", (0xFF & bt)).toUpperCase();
	}
	public static String toHex(Integer value) {
		String result = "0000";
		result += Integer.toHexString(value & 0x0ffffff);
		
		return result.substring(result.length()-4);
	}
	public static String toHexPlain(byte bt)
	{
		return String.format("%02x", (0xFF & bt)).toUpperCase();
	}

	private static final char kHexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static void appendHexPair(byte b, StringBuilder hexString)
	{
		char highNibble = kHexChars[(b & 0xF0) >> 4];
		char lowNibble = kHexChars[b & 0x0F];
		hexString.append("0x");
		hexString.append(highNibble);
		hexString.append(lowNibble);
	}
 
//	private static void appendHexPairNoFormatting(byte b, StringBuilder hexString)
//	{
//		char highNibble = kHexChars[(b & 0xF0) >> 4];
//		char lowNibble = kHexChars[b & 0x0F];
//		hexString.append(highNibble);
//		hexString.append(lowNibble);
//	}
 
	public static String toHexArray(byte[] array)
	{
		if (array == null)
			return "null";
		StringBuilder sb = new StringBuilder();
		for (byte b : array)
		{
			appendHexPair(b, sb);
			sb.append(' ');
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public static int unsignedByteToInt(byte b)
	{
		return b & 0xFF;
	}

	public static String toHexArrayShort(int[] values)
	{
		String result = "";
		for (int value : values) {
			result += toHexArray((short)(value & 0x0FFFF));
			result += ", ";
		}
		if (result.endsWith(", "))
			result = result.substring(0, result.length() - 2 );
		return result;
	}


	public static String toHex(Integer value, boolean limitLen) {
		String result = "000000";
		result += Integer.toHexString(value & 0x0ffffff);

		return result.replaceFirst("^0+(?!$)", "");
	}

	public static String toHexArray(int value, boolean limitLen) {
		if (value > 0x0ffffff) {
			byte[] b = new byte[4];
			b[0] = (byte)((value >> 3*8 )  & 0x0ff);
			b[1] = (byte)((value >> 2*8 )  & 0x0ff);
			b[2] = (byte)((value >> 1*8 )  & 0x0ff);
			b[3] = (byte)((value)  & 0x0ff);
			
			return toUnformattedHexArray(b);
		} else if (value > 0x0ffff) {
			byte[] b = new byte[3];
			b[0] = (byte)((value >> 2*8 )  & 0x0ff);
			b[1] = (byte)((value >> 1*8 )  & 0x0ff);
			b[2] = (byte)((value)  & 0x0ff);
			
			return toUnformattedHexArray(b);
		} else if (value > 0x0ff) {
			byte[] b = new byte[2];
			b[0] = (byte)((value >> 1*8 )  & 0x0ff);
			b[1] = (byte)((value)  & 0x0ff);
			
			return toUnformattedHexArray(b);
		} else {
			byte[] b = new byte[1];
			b[0] = (byte)((value)  & 0x0ff);
			
			return toUnformattedHexArray(b);
		}
	}

	public static String toHexArray(int value)
	{
		byte[] b = new byte[4];
		b[0] = (byte)((value >> 3*8 )  & 0x0ff);
		b[1] = (byte)((value >> 2*8 )  & 0x0ff);
		b[2] = (byte)((value >> 1*8 )  & 0x0ff);
		b[3] = (byte)((value)  & 0x0ff);
		
		return toUnformattedHexArray(b);
	}

	public static String toHexArray(short value)
	{
		byte[] b = new byte[2];
		b[0] = (byte)((value >> 1*8 )  & 0x0ff);
		b[1] = (byte)((value)  & 0x0ff);
		
		return toUnformattedHexArray(b);
	}

	public static String toUnformattedHexArray(byte[] array)
	{
		if (array == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (byte b : array)
		{
			appendHexPairUnformatted(b, sb);
		}
		return sb.toString();
	}

	public static String toUnformattedHexArray(byte[] array, String prefix)
	{
		if (array == null)
			return "";
		if (prefix == null)
			prefix = "";

		StringBuilder sb = new StringBuilder();
		for (byte b : array)
		{
			sb.append(prefix);
			appendHexPairUnformatted(b, sb);
		}
		return sb.toString();
	}

	public static String toKannelString(byte[] array) {
		return toUnformattedHexArray(array,"%");
	}

	private static void appendHexPairUnformatted(byte b, StringBuilder hexString)
	{
		char highNibble = kHexChars[(b & 0xF0) >> 4];
		char lowNibble = kHexChars[b & 0x0F];
		// hexString.append("0x");
		hexString.append(highNibble);
		hexString.append(lowNibble);
	}
	
	public static byte[] hexStringToByteArray(String s) {
		s = s.replaceAll(" ", "");
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}

	public static int hexStringToByte(String s) {
		s = s.replaceAll(" ", "");
		if (s.length() < 1) {
			return 0;
		}
		if (s.length() == 1) {
			return Character.digit(s.charAt(0), 16);
		}
		
		return ((Character.digit(s.charAt(0), 16) << 4)
				+ Character.digit(s.charAt(1), 16));
	}

	public static int hexStringToShort(String s) {
		s = s.replaceAll(" ", "");
		if (s.length() < 1) {
			return 0;
		}
		s = "0000" + s;
		s = s.substring(s.length()-4);
		
		return (
				(Character.digit(s.charAt(0), 16) << 12) +
				(Character.digit(s.charAt(1), 16) << 8) +
				(Character.digit(s.charAt(2), 16) << 4) +
				(Character.digit(s.charAt(3), 16))
				);
	}

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
	
	public static byte[] hexToArray(String hexStr) {
	    int i;
        StringBuilder sb = new StringBuilder();

        // Se hace esto en lugar de usar String.replace porque mejora much�simo el performance
        for (i  = 0; i < hexStr.length(); i++) {
            switch (hexStr.charAt(i)) {
                case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
                case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                    sb.append(hexStr.charAt(i));
                    break;
            }
        }

        if (sb.length() % 2 != 0) { sb.insert(0, '0'); }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(sb.length()/2);
        for (i = 0; i < sb.length(); i += 2) {
            baos.write(new byte[]{(byte)Integer.parseInt(sb.substring(i, i+2), 16)}, 0, 1);
        }
        return baos.toByteArray();
	}
    /*Convierte un array de datos en hexadecimal a otro array de datos en hexadecimal pero con "numBytes" bytes*/
    public static String convStringTo_N_HexBytes(String data, int numBytes) {
        while (data.length() < (2 * numBytes)) {
            data = "0".concat(data);
        }

        return data;
    }

	public static String getFileContent(String pcFileCommand, Boolean convertToHexString) {
		String result = null;
		Path path = Paths.get(pcFileCommand);
		try {
			byte[] data = Files.readAllBytes(path);
			if (convertToHexString) {
				result = Utils.toUnformattedHexArray(data, "");
			} else {
				result = new String(data, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String randomHex() {
		byte[] ba = new byte[16];
		new Random().nextBytes(ba);
		return Utils.toUnformattedHexArray(ba);
	}
	
	
	public static String decrypt3Des(String key, String input)throws Exception{
		byte[] tmp = h2b(key);
	    byte[] key1 = new byte[24];
	    System.arraycopy(tmp, 0, key1, 0, 16);
	    System.arraycopy(tmp, 0, key1, 16, 8);
	    Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
	    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key1, "DESede"));
	    byte[] plaintext = cipher.doFinal(h2b(input));
	    return b2h(plaintext);
	}
	
	private static byte[] h2b(String hex)
	  {
	    if ((hex.length() & 0x01) == 0x01)
	      throw new IllegalArgumentException();
	    byte[] bytes = new byte[hex.length() / 2];
	    for (int idx = 0; idx < bytes.length; ++idx) {
	      int hi = Character.digit((int) hex.charAt(idx * 2), 16);
	      int lo = Character.digit((int) hex.charAt(idx * 2 + 1), 16);
	      if ((hi < 0) || (lo < 0))
	        throw new IllegalArgumentException();
	      bytes[idx] = (byte) ((hi << 4) | lo);
	    }
	    return bytes;
	  }

	  private static String b2h(byte[] bytes)
	  {
	    char[] hex = new char[bytes.length * 2];
	    for (int idx = 0; idx < bytes.length; ++idx) {
	      int hi = (bytes[idx] & 0xF0) >>> 4;
	      int lo = (bytes[idx] & 0x0F);
	      hex[idx * 2] = (char) (hi < 10 ? '0' + hi : 'A' - 10 + hi);
	      hex[idx * 2 + 1] = (char) (lo < 10 ? '0' + lo : 'A' - 10 + lo);
	    }
	    return new String(hex);
	  }
	  
		public static String rotateIccid(String iccidRotate){
			String iccidOk="";
			int count=0;
			if(iccidRotate.length()%2==0){
				iccidRotate=iccidRotate.replace("F", "");
				for(int i=0;i<iccidRotate.length()-1;i+=2){
					iccidOk=iccidOk+iccidRotate.charAt(i+1)+iccidRotate.charAt(i);
					count=i;
				}
				try {
					iccidOk=iccidOk+iccidRotate.charAt(count+2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else{
				for(int i=0;i<iccidRotate.length()-1;i+=2){
					iccidOk=iccidOk+iccidRotate.charAt(i+1)+iccidRotate.charAt(i);
					count=i;
				}
				iccidOk=iccidOk+iccidRotate.charAt(count+2);
			}		
			return iccidOk;
		}
		
		public static String fechaActual() {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat formatoFecha = new SimpleDateFormat();
			formatoFecha.setTimeZone(TimeZone.getTimeZone("GMT-6"));
			Date fechaSum = calendar.getTime();
			formatoFecha.applyPattern("dd/MM/yyyy");
			String fechaRespuesta = formatoFecha.format(fechaSum);
			return fechaRespuesta;
		}
		
		public static Integer TrimestreActual() {
			Integer fechaActual = obtenerTrimestre(fechaActual());
			return fechaActual;
			
		}

		public static Calendar fechaACalendar(String fecha) {
			System.out.println("fecha del timestre: "+fecha);
			String[] fechArray = fecha.split("/");
			int dia = Integer.valueOf(fechArray[1]);
			int mes = Integer.valueOf(fechArray[0]) - 1;
			int anio = Integer.valueOf(fechArray[2]);
			Calendar c1 = new GregorianCalendar(anio, mes, dia);
			return c1;
		}

		public static Integer obtenerTrimestre(String fecha) {
			Calendar fechaActual = fechaACalendar(fecha);
			Calendar fechaInicioPrimerTrimestre = fechaACalendar(Settings.getInstance().getInicio_primer_timestre());
			Calendar fechaInicioSegundoTrimestre = fechaACalendar(Settings.getInstance().getInicio_segundo_timestre());
			Calendar fechaInicioTercerTrimestre = fechaACalendar(Settings.getInstance().getInicio_tercer_timestre());
			Calendar fechaFinTercerTrimestre = fechaACalendar(Settings.getInstance().getFin_tercer_timestre());
			if(fechaActual.after(fechaInicioPrimerTrimestre) && fechaActual.before(fechaInicioSegundoTrimestre))
				return Nota.PRIMER_TRIMESTRE;
			if(fechaActual.after(fechaInicioSegundoTrimestre) && fechaActual.before(fechaInicioTercerTrimestre))
				return Nota.SEGUNDO_TRIMESTRE;
			if(fechaActual.after(fechaInicioTercerTrimestre) && fechaActual.before(fechaFinTercerTrimestre))
				return Nota.TERCER_TRIMESTRE;
			return null;
		}
	

}
