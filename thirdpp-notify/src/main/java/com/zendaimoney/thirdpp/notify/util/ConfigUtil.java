package com.zendaimoney.thirdpp.notify.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang.StringUtils;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import com.zendaimoney.thirdpp.notify.conf.ChannelConfig;
import com.zendaimoney.thirdpp.notify.conf.SystemConfig;
import com.zendaimoney.thirdpp.notify.exception.PlatformException;

public class ConfigUtil implements Serializable {

	private static final long serialVersionUID = 8433608222120950801L;

	private static final LogPrn log = new LogPrn(ConfigUtil.class);

	/**
	 * 通道集合
	 */
	public static HashMap<String, ChannelConfig> channelMap = new HashMap<String, ChannelConfig>();

	private SystemConfig systemConfig;

	/**
	 * 加载指定路径下配置文件
	 * 
	 * @param path
	 */
	public void loadFromFile(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				log.error("File " + path + " is not exists");
				throw new PlatformException("File " + path + " is not exists");
			}
			Ini conf = new Ini(file);
			this.populateAttributes(conf);
			log.info("Load file " + path + " sucessful");
		} catch (IOException e) {
			log.error("Parse configuration failed,path=" + path);
			throw new PlatformException("Parse configuration failed,path="
					+ path, e);
		} catch (Exception e) {
			log.error("Parse configuration failed,path=" + path);
			throw new PlatformException("Parse configuration failed,path="
					+ path, e);
		}

	}

	private void populateAttributes(Ini conf) {
		this.populateSystemConf(conf);
	}

	private void populateSystemConf(Ini conf) {
		Section section = conf.get("system");
		if (section != null) {
			systemConfig = new SystemConfig();
			systemConfig.setAppName(section.get("appName"));		
			systemConfig.setNotifySleepTime(Long.valueOf(section.get("notifySleepTime")));
			systemConfig.setMergeSleepTime(Long.valueOf(section.get("mergeSleepTime")));
			systemConfig.setQueryWaitingSleepTime(Long.valueOf(section.get("queryWaitingSleepTime")));
			systemConfig.setNotifyQueryCount(Integer.valueOf(section.get("notifyQueryCount")));
			systemConfig.setWaitingQueryCount(Integer.valueOf(section.get("waitingQueryCount")));
			systemConfig.setNotifyNumber(Integer.valueOf(section.get("notifyNumber")));
			systemConfig.setMerge_offline_key(section.get("merge_offline_key"));
			systemConfig.setMerge_online_key(section.get("merge_online_key"));
			
			// 设置全局变量
			systemConfig.setGlobalConfigPath(System.getenv("TPPGLOBALCONFIGPATH"));
		}
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public HashMap<String, ChannelConfig> getChannelMap() {
		return channelMap;
	}

	/**
	 * 根据main参数获取文件路径(格式: -f 路径)
	 * 
	 * @param args
	 * @return
	 */
	public static String getConfigFilePath(String[] args) {
		Options options = new Options();
		Option file = new Option("f", true, "Configuration file path");
		options.addOption(file);
		CommandLineParser parser = new PosixParser();
		CommandLine line = null;
		try {
			line = parser.parse(options, args);
		} catch (ParseException e) {
			throw new PlatformException("Parse command line failed", e);
		}
		String configFilePath = null;
		if (line.hasOption("f")) {
			configFilePath = line.getOptionValue("f");
		} else {
			System.err
					.println("Please tell me the configuration file path by -f option");
			System.exit(1);
		}
		if (StringUtils.isBlank(configFilePath)) {
			throw new PlatformException("Blank file path");
		}
		return configFilePath;
	}

	public static void main(String args[]) {
	}

}