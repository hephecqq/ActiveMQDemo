package com.dagong.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 实现二维码的生成
 * 
 * @author Administrator
 *
 */
public class ZxingTest {

	/**
	 * 如何生产二维码
	 */

	private final int width = 300;
	private final int height = 300;
	private final String contents = "www.hepeng.com";
	private BitMatrix bitMatrix;
	private final String format = "png";

	static HashMap hints = new HashMap();

	static{
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);

	}
	@Test
	public void testCreateQRCodes() throws WriterException, IOException {
		// 定义二维码的参数
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);

		bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
		Path file = new File("d:/test.png").toPath();
		MatrixToImageWriter.writeToPath(bitMatrix, format, file);

	}
	/**
	 * 使用zxing进行二维码解析
	 * @throws IOException 
	 * @throws NotFoundException 
	 * 
	 */
	public void testParseQRCode() throws IOException, NotFoundException{
		MultiFormatReader formatReader =new MultiFormatReader();
		File file =new File("d:/test.png");
		BufferedImage bufferedImage=ImageIO.read(file);
		BinaryBitmap binaryBitmap =new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
		Result result=formatReader.decode(binaryBitmap,hints);
		System.out.println("解析结构："+result.toString());
		System.out.println("二维码格式:"+result.getBarcodeFormat());
		System.out.println("二维码文本内容："+result.getText());
		
	}
}
