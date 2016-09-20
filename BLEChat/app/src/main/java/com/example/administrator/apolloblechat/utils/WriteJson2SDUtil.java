package com.example.administrator.apolloblechat.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/****
 * 往SD卡中写入数据
 * 
 * @author Administrator
 * 
 */
public class WriteJson2SDUtil {
	/*****
	 * 
	 * @param json
	 *            需要写的数据
	 * @param name
	 *            名字
	 */
	public static void writeJson(String json, String name) {

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {// 判断是否存在SD卡
			return;
		}
		File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "crash_apollo_chat" + File.separator + name + ".txt");
		if (!file.getParentFile().exists()) {// 判断父文件是否存在，如果不存在则创建
			file.getParentFile().mkdirs();
		}
		PrintStream out = null; // 打印流
		try {
			out = new PrintStream(new FileOutputStream(file)); // 实例化打印流对象
			out.print(json.toString()); // 输出数据
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) { // 如果打印流不为空，则关闭打印流
				out.close();
			}
		}
	}

	/*****
	 * 写到SD卡的某个文件夹下
	 * 
	 * @param json
	 * @param date
	 * @param fileName
	 */
	public static void writeJson(String json, String date, String fileName) {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {// 判断是否存在SD卡
			return;
		}
		File file = new File(Environment.getExternalStorageDirectory()
				.toString() + File.separator + fileName + ".txt");
		if (!file.getParentFile().exists()) {// 判断父文件是否存在，如果不存在则创建
			file.getParentFile().mkdirs();
		}
		PrintStream out = null; // 打印流
		try {
			out = new PrintStream(new FileOutputStream(file)); // 实例化打印流对象
			out.print(json.toString() + "\n" + date + "\n"); // 输出数据
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) { // 如果打印流不为空，则关闭打印流
				out.close();
			}
		}
	}

}
