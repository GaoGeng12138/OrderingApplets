package com.gaog.orderingapplets.restaurant.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ProjectName: OrderingApplets
 * @PACKAGE_NAME： com.gaog.orderingapplets.restaurant.util
 * @Date：2024/12/25 15:33
 * @Version： 1.0.0
 * @Description： word工具转换类
 * @Author： ZSJ
 */
@Slf4j
public class WordUtil {

    /**
     * 功能描述： 将 PDF 转换为 Word
     *
     * @param pdfFilePath  PDF 文件路径
     * @param wordFilePath Word 文件路径
     * @Author： ZSJ
     */
    public static void convertPDFtoWord(String pdfFilePath, String wordFilePath) {
        PDDocument pdfDocument = null;
        XWPFDocument wordDocument = null;
        try {
            // 读取PDF文件
            pdfDocument = Loader.loadPDF(new File(pdfFilePath));
            // 创建Word文档
            wordDocument = new XWPFDocument();
            // 提取PDF的每一页
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdfDocument);
            // 创建段落并添加文本
            XWPFParagraph paragraph = wordDocument.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(text);
            // 保存Word文档
            wordDocument.write(new FileOutputStream(wordFilePath));
            // 关闭文档
            wordDocument.close();
            pdfDocument.close();
        } catch (IOException e) {
            log.error("转换 PDF 到 Word 失败异常：{}", e.getMessage());
        }
    }

    /**
     * 功能描述：方法2 将 PDF 转换为 Word
     * todo 问题版本
     * @param pdfFilePath  PDF 文件路径
     * @param wordFilePath Word 文件路径
     * @Author： ZSJ
     */
    public static void convertPdfToWord(String pdfFilePath, String wordFilePath) {
        try {
            // 加载 PDF 文档
            PDDocument pdfDocument = Loader.loadPDF(new File(pdfFilePath));
            PDFTextStripper textStripper = new PDFTextStripper();
            String pdfText = textStripper.getText(pdfDocument);

            // 创建 Word 文档
            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            ObjectFactory factory = new ObjectFactory();
            Text textElement = factory.createText();
            textElement.setValue(pdfText);
            wordPackage.getMainDocumentPart().addParagraphOfText(pdfText);

            // 保存为 Word 文件
            wordPackage.save(new File(wordFilePath));
            log.info("转换完成！文件保存在：{}" , wordFilePath);
        } catch (Exception e) {
            log.error("发生错误：{}", e.getMessage());
        }
    }

    public static void main(String[] args) {
        String pdfFilePath = "C:\\Users\\Plus maintenant\\Documents\\WeChat Files\\wxid_6hmqr8rc0ufm22\\FileStorage\\File\\2024-12\\简历-王君.pdf";
        String wordFilePath = "C:\\Users\\Plus maintenant\\Desktop\\简历-王君.docx";
        convertPdfToWord(pdfFilePath, wordFilePath);
    }
}
