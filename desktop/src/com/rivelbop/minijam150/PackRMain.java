package com.rivelbop.minijam150;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;

public class PackRMain {
	public static void main(String[] args) throws IOException, CompressorException, ArchiveException {
		PackrConfig config = new PackrConfig();
		config.platform = PackrConfig.Platform.MacOS;
		//config.jdk = "/Users/davidjerzak/Downloads/jdk1.8.0_202.jdk";
		//config.jrePath = "/Users/davidjerzak/Downloads/jdk1.8.0_202.jdk/Contents/Home/jre";
		//config.jdk = "/Users/davidjerzak/Downloads/jdk8u402-b06";
		//config.jrePath = "/Users/davidjerzak/Downloads/jdk8u402-b06/Contents/Home/jre";
		config.jdk = "jdk8u402-b06";
		config.jrePath = "jdk8u402-b06/Contents/Home/jre";
		config.executable = "Long Lost Wizard";
		config.classpath = Arrays.asList("/Users/davidjerzak/Documents/GitHub/Mini-Jam-150/desktop/build/libs/desktop-1.0.jar");
		config.removePlatformLibs = config.classpath;
		config.mainClass = "com.rivelbop.minijam150.DesktopLauncher";
		config.vmArgs = Arrays.asList("Xmx1G", "-XstartOnFirstThread");
		config.minimizeJre = "hard";
		config.outDir = new java.io.File("out-mac");
		config.useZgcIfSupportedOs = true;

		new Packr().pack(config);
	}
}
