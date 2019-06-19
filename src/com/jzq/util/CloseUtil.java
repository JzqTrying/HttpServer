package com.jzq.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流的方法
 * @author 25689
 *
 */
public class CloseUtil {
	public static void closeAll(Closeable...io) {
		for(Closeable temp:io) {
			if(null!=temp) {
				try {
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
