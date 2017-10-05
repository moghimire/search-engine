package com.goglides.esservice.clientservice;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.goglides.esservice.util.ConfigFileParser;
import com.goglides.esservice.util.Configuration;

public class ClientService {
	// private static final Logger logger =
	// LoggerFactory.getLogger(ClientServiceImpl.class);

	private static Client client;

	public static Client getClient() {
		if (client == null) {
			client = createClient();
		}

		return client;
	}

	protected static Client createClient() {
		if (client == null) {
			try {
				ConfigFileParser.parseConfigFile("config.properties");
				Settings settings = Settings.builder().put("cluster.name", Configuration.CLUSTER_NAME).build();
				client = new PreBuiltTransportClient(settings).addTransportAddress(
						new InetSocketTransportAddress(InetAddress.getByName(Configuration.HOST), Configuration.PORT));

			} catch (Exception ex) {
				System.out.println("Error occured while creating search client!" + ex);
			}
		}
		return client;
	}

	public static void closeClient() {
		if (client != null) {
			client.close();
		}
	}
}
