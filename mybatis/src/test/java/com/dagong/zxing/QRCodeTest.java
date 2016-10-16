package com.dagong.zxing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

/**
 * 使用QRCoded
 * @author Administrator
 *
 */
public class QRCodeTest {

	
	@Test
	public void test1() throws FileNotFoundException, Exception{
		QRCodeUtil.encode("hepeng", new FileOutputStream(new File("d:/test/test1.png")));
	}
	
	@Test
	public void test2() throws FileNotFoundException, Exception{
		QRCodeUtil.encode("zhangsan", "D:\\我的文档\\My Pictures\\12.jpg", "d:/test/test2.png");
	}
}
