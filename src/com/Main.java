package com;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public int search(String s) throws Exception {
    Pattern pattern = Pattern.compile(s);
    FileInputStream input = new FileInputStream("/home/vinay/workspace/M-Tech-DP/src/access_log_Jul95");
    FileChannel channel = input.getChannel();
    ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) channel.size());
    CharBuffer cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
    Matcher matcher = pattern.matcher(cbuf);
    int count = 0;
    while (matcher.find()) {
String match = matcher.group();
count++;
//System.out.println(match);
    }
    return count;
}
}

