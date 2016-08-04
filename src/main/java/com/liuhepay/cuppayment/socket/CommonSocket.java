package com.liuhepay.cuppayment.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import android.os.SystemClock;


public class CommonSocket
{
	public static final int		ERR_PARAMETERS				= -9001;
	public static final int		ERR_CONNECT_FAILED			= -9002;
	public static final int		ERR_BUILD_CONNECTOIN_FIRST	= -9003;
	public static final int		ERR_SEND_REQ_FAILED			= -9004;
	public static final int		ERR_RECEIVE_RSP_FAILED		= -9005;
	public static final int		ERR_RECEIVE_TIMEOUT			= -9006;
	public static final int		ERR_CLOSE_EXCEPTION			= -9007;
	public static final int		ERR_UNKNOWNHOST				= -9021;

	private static Socket		sk							= null;
	private static InputStream	IS							= null;
	private static OutputStream	OS							= null;
	private static String		TAG							= "CommonSocket";

	public CommonSocket()
	{

	}

	/**
	 * 
	 * @param ip
	 *            remote host ip
	 * @param port
	 *            remote host port
	 * @return 0 connect successful; others are failed
	 */
	public static int connectToHost(String ip, int port, int timeOut)
	{
		if (sk != null)
		{
			if (IS != null)
			{
				try
				{
					IS.close();
					OS.close();
					sk.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return ERR_CLOSE_EXCEPTION;
				}
			}
		}
		try
		{
			sk = new Socket();
			sk.connect(new InetSocketAddress(ip, port), timeOut * 1000);
			if (sk != null)
			{
				IS = new DataInputStream(sk.getInputStream());
				OS = new DataOutputStream(sk.getOutputStream());

				if (IS == null || OS == null)
				{
					close();
					return ERR_CONNECT_FAILED;
				}
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			return ERR_UNKNOWNHOST;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			close();
			return ERR_CONNECT_FAILED;
		}

		return 0;
	}

	/**
	 * 
	 * @param reqMsg
	 *            request message that will be send to the remote
	 * @param reqMsgLen
	 *            request message's length
	 * @param timeOutMs
	 *            NO use just in case for reserved
	 * @return 0 send successful; others are failed
	 */
	public static int sendBytes(byte[] reqMsg, int reqMsgLen, long timeOutMs)
	{
		if (reqMsg == null || reqMsgLen <= 0)
		{
			return ERR_PARAMETERS;
		}

		if (OS == null)
		{
			close();
			return ERR_BUILD_CONNECTOIN_FIRST;
		}
		TimeOutTask task = new TimeOutTask();
		Timer timeout = new Timer();
		timeout.schedule(task, timeOutMs);

		try
		{
			OS.write(reqMsg, 0, reqMsgLen);
		}
		catch (IOException e)
		{
			close();
			e.printStackTrace();
			return ERR_SEND_REQ_FAILED;
		}
		finally
		{
			timeout.cancel();
		}

		return 0;
	}

	/**
	 * 
	 * @param rspMsg
	 *            storage the data
	 * @param wantedLen
	 *            expected receive data length
	 * @param timeOutMs
	 *            receive timeout milliseconds
	 * @return < 0 some errors occurs; >=0 received data's length
	 */
	public static int receiveBytes(byte[] rspMsg, int wantedLen, long timeOutMs)
	{
		int reveiveLen = 0;
		int readLenPerTime = 0;
		TimeOutTask task = new TimeOutTask();
		Timer timeout = new Timer();
		timeout.schedule(task, timeOutMs);
		if (rspMsg == null)
		{
			close();
			return ERR_PARAMETERS;
		}

		if (IS == null)
		{
			close();
			return ERR_BUILD_CONNECTOIN_FIRST;
		}

		while (wantedLen > 0)
		{
			try
			{
				if (IS.available() > 0)
				{
					readLenPerTime = IS.read(rspMsg, reveiveLen, wantedLen);
					wantedLen -= readLenPerTime;
					reveiveLen += readLenPerTime;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return ERR_CONNECT_FAILED;
			}
			SystemClock.sleep(50);
		}
		task.cancel();
		return reveiveLen;
	}

	/**
	 * close the socket
	 */
	public static void close()
	{
		try
		{
			if (OS != null)
			{
				OS.close();
				//				OS = null;
			}
			if (IS != null)
			{
				IS.close();
				//				IS = null;
			}
			if (sk != null)
			{
				sk.close();
				//				sk = null;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	static class TimeOutTask extends TimerTask
	{

		@Override
		public void run()
		{
			close();
		}

	}
}
