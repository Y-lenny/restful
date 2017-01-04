/*
 * Copyright 2010, eCollege, Inc.  All rights reserved.
 */
package org.restexpress.pipeline;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Provides a tiny DSL to define the pipeline features.
 *
 * @author toddf
 * @since Aug 27, 2010
 */
public class PipelineInitializer
extends ChannelInitializer<SocketChannel>
{
	// SECTION: CONSTANTS

	private static final int DEFAULT_MAX_CONTENT_LENGTH = 20480;

	// SECTION: INSTANCE VARIABLES

	private List<ChannelHandler> requestHandlers = new ArrayList<ChannelHandler>();
	private int maxContentLength = DEFAULT_MAX_CONTENT_LENGTH;
	private EventExecutorGroup eventExecutorGroup = null;
	private SSLContext sslContext = null;
	private boolean useCompression = true;

	// SECTION: CONSTRUCTORS

	public PipelineInitializer()
	{
		super();
	}

	// SECTION: BUILDER METHODS

	public PipelineInitializer addRequestHandler(ChannelHandler handler)
	{
		if (!requestHandlers.contains(handler))
		{
			requestHandlers.add(handler);
		}

		return this;
	}

	public PipelineInitializer setExecutionHandler(EventExecutorGroup executorGroup)
	{
		this.eventExecutorGroup = executorGroup;
		return this;
	}

	/**
	 * Set the maximum length of the aggregated (chunked) content. If the length
	 * of the aggregated content exceeds this value, a TooLongFrameException
	 * will be raised during the request, which can be mapped in the RestExpress
	 * server to return a BadRequestException, if desired.
	 *
	 * @param value
	 * @return this PipelineBuilder for method chaining.
	 */
	public PipelineInitializer setMaxContentLength(int value)
	{
		this.maxContentLength = value;
		return this;
	}

	public PipelineInitializer setSSLContext(SSLContext sslContext)
	{
		this.sslContext = sslContext;
		return this;
	}

	public SSLContext getSSLContext()
	{
		return sslContext;
	}

	// SECTION: CHANNEL PIPELINE FACTORY

	@Override
	public void initChannel(SocketChannel ch) throws Exception
	{
		ChannelPipeline pipeline = ch.pipeline();

		if (null != sslContext)
		{
			SSLEngine sslEngine = sslContext.createSSLEngine();
			sslEngine.setUseClientMode(false);
			SslHandler sslHandler = new SslHandler(sslEngine);
			pipeline.addLast("ssl", sslHandler);
		}

		// Inbound handlers
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("inflater", new HttpContentDecompressor());

		// Outbound handlers
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("chunkWriter", new ChunkedWriteHandler());

		if (useCompression)
		{
			pipeline.addLast("deflater", new HttpContentCompressor());
		}

		// Aggregator MUST be added last, otherwise results are not correct
		pipeline.addLast("aggregator", new HttpObjectAggregator(maxContentLength));

		addAllHandlers(pipeline);
	}

	private void addAllHandlers(ChannelPipeline pipeline)
    {
		if (eventExecutorGroup != null)
		{
			for (ChannelHandler handler : requestHandlers)
			{
				pipeline.addLast(eventExecutorGroup, handler.getClass().getSimpleName(), handler);
			}
		}
		else
		{
		    for (ChannelHandler handler : requestHandlers)
			{
				pipeline.addLast(handler.getClass().getSimpleName(), handler);
			}
		}
    }

	public ChannelHandler setUseCompression(boolean shouldUseCompression)
	{
		this.useCompression = shouldUseCompression;
		return this;
	}
}
