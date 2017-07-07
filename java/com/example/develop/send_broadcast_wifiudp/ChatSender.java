package com.example.develop.send_broadcast_wifiudp;

/**
 * Created by JimmyLiang on 2017/7/5.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class ChatSender extends Thread{

    private static final int SEND_TO_PORT = 8000;
    String str;

    public void run(){
        DatagramSocket socket = null;
        try
        {
            //Socket與的設定與建立
            //DatagramSocket為接收的port，此為傳送方所以設定null讓程式自己搜尋可用的port即可
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("192.168.43.255"); //傳送指定目標IP位置，如最後設定255 or 0則會向同網域內的裝置全部廣播
            Log.d("IP Address", serverAddress.toString());

            //假設EditText內沒輸入文字則傳送預設內容，如有輸入文字就用輸入文字當傳送內容
            if(!MainActivity.editText.getText().toString().trim().equals("")) {
                str = MainActivity.editText.getText().toString();
            }else {
                str = "Test Thread sender";
            }

            //創建一個用於發送的DatagramPacket對象
            DatagramPacket packet=new DatagramPacket(str.getBytes(),str.length(),serverAddress,SEND_TO_PORT);
            //發送數據
            while(true)
            {
                Log.i("SEND","sending");
                socket.send(packet);
                Thread.sleep(1000);
            }
        }
        catch(SocketException e)
        {
            e.printStackTrace();
            String error = e.toString();
            Log.e("Error by Sender", error);
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
            String error = e.toString();
            Log.e("Error by Sender", error);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            String error = e.toString();
            Log.e("Error by Sender", error);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String error = e.toString();
            Log.e("Error by Sender", error);
        } finally{
            if(socket != null){
                socket.close();
            }

        }
    }
}
