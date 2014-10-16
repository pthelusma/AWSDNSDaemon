package com.pierrethelusma;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;


public class AWSDNSDaemon implements Daemon {
	
	private Thread daemonThread;
	private TimerTask timerTask;
	private Timer timer;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		daemonThread = null;
	}

	@Override
	public void init(DaemonContext daemonContext) throws DaemonInitException, Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		String[] args = daemonContext.getArguments();
		
		daemonThread = new Thread(){
			public synchronized void start(){
				super.start();
			}
			
			public void run(){
				timer = new Timer(true);
				timerTask = new TimerTask() {
					
					@Override
					public void run() {
						AWSDNSMonitor.monitorAWSDNS();
					}
				};
				
				timer.scheduleAtFixedRate(timerTask, 0, 60 * 1000);
			}
		};

	}

	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub
		daemonThread.start();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
	}

}
