package com.yjy.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}


	// 文件上传相关代码
	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (file.isEmpty()) {
			return "文件为空";
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		
		String filePath = "E://test//"; // 文件上传后的路径
		// fileName = UUID.randomUUID() + suffixName; // 解决中文问题，liunx下中文路径，图片显示问题
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			return "上传成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传失败";
	}

	// 文件下载相关代码
	@RequestMapping("/download")
	public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fileName = "testDownLoad.docx";
		if (fileName != null) {
			// 当前是从该工程的WEB-INF//files/下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
			String realPath = request.getServletContext().getRealPath("//WEB-INF/files/");
			System.out.println(realPath);
			File file = new File(realPath, fileName);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					logger.info(fileName + " download is success!");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) bis.close();
					if (fis != null) fis.close();
				}
			}
		}
		return null;
	}

	// 多文件上传
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		
		String filePath = "E:/test/"; // 文件上传后的路径 需要先在"E盘创建test文件夹"先
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(filePath, file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}
}
