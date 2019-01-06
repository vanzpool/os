package nachos.mainProcess;

import nachos.machine.Machine;
import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.threads.Semaphore;

public class MyNetworkLink {
	
	private Semaphore semaphore;
	private NetworkLink networkLink;
	
	public MyNetworkLink(){
		semaphore = new Semaphore(0);
		networkLink = Machine.networkLink();
		
		Runnable recv = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Packet recvPacket = networkLink.receive();
				if(recvPacket != null){
					String message = new String(recvPacket.contents);
					System.out.println("[From : " + recvPacket.srcLink +"]"
							+ message);
				}
				
			}
		};
		
		Runnable send = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				semaphore.V();
			}
		};
		
		networkLink.setInterruptHandlers(recv, send);
	}
	
	public void send(int dest , String message){
		try {
			Packet packet = new Packet(dest, getAddress(), message.getBytes());
			networkLink.send(packet);
			semaphore.P();
		} catch (MalformedPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public int getAddress(){
		return networkLink.getLinkAddress();
	}
	
}
