/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.GridPeer;
import BruteForceGrid.DataCollection.NetData;
import BruteForceGrid.DataCollection.PeerInfo;
import BruteForceGrid.DataCollection.xMessenger;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class DecryptorDownloader
{
    public static boolean Get(String DecryptorDiscr, GridPeer gridPeer)
    {
        File file = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        Socket s = null;
        
        try
        {
            /**
             * open connection to the remotefile transfer server
             */
            s = new Socket(gridPeer.getAddress(),
                    CommonRegister.FILE_TRASFER_PORT());
            /**
             * opens a writer and write wht is the decryptor that wana be downloaded
             * and clse the connection writer
             */
            xMessenger.miniMessege(xMessenger.SENDING_FILE_REQUEST);
            
            writer = new PrintWriter(s.getOutputStream(), true);
            writer.println(DecryptorDiscr);
            writer.flush();
            
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            xMessenger.miniMessege(xMessenger.WAITING_FORFILE_RESPONSE);
            
            while(!reader.ready())
            {
                Thread.sleep(1000);
            }
            String str = reader.readLine();
            int sizeofFile = Integer.parseInt(str.split(NetData.DELEMETER)[0]);
            str = str.split(NetData.DELEMETER)[1];

            if(str.equals(NetData.TRUE))
            {
                xMessenger.miniMessege(xMessenger.ACK_FROM_FILE_SERVER);
            }
            else
            {
                xMessenger.miniMessege(xMessenger.NACK_FROM_FILE_SERVER);
                return false;
            }
            
            /**
             * open a stream to read the file content
             */
            bis = new BufferedInputStream(s.getInputStream());
            /**
             * crate the file discrptor
             */
            file = new File(CommonRegister.GET_DECRYPTOR_PATH(DecryptorDiscr,
                    PeerInfo.getOS()));
            /**
             * if file already excist.. then delete it
             * next create a new file
             */
            
            file.delete();
            file.createNewFile();
            
            /**
             * crate a file output stream to write to local file
             */
            bos = new BufferedOutputStream(new FileOutputStream(file));

            xMessenger.miniMessege(xMessenger.WAITING_FOR_FILE_TRASFER);

            /**
             * ask to start the transaction
             */
            writer.println(NetData.TRUE);
            writer.flush();
            /**
             * the timer for waiting untill the stream is
             * ready to read data
             */
            int count =0;
            while(bis.available()<=0)
            {
                Thread.sleep(1000);
                count++;
                /**
                 * if more than da defined tym taken.. da process quits.
                 */
                if(count>CommonRegister.TIME_TO_AWAIT_UNTIL_DOWNLOAD)
                {
                    bis.close();
                    bos.close();
                    return false;
                }
            }

            xMessenger.miniMessege(xMessenger.FILE_TRASFER_STARTED);

            /**
             * fiel trasfering process is done..
             */
            byte[] bs = new byte[CommonRegister.SIZE_OF_FILE_READBLE_AT_A_TIME];

            int totalReadData = 0;
            int readData = 0;
            while(totalReadData < sizeofFile)
            {
                readData = bis.read(bs);
                totalReadData += readData;
                bos.write(bs, 0, readData);
            }
            
            xMessenger.miniMessege(xMessenger.FILE_TRASFER_COMPLETED);

            /**
             * streams closed
             */

            s.close();
            bos.close();
            bis.close();

            /**
             * MD5 check....
             */
            String s1 = DecryptorDownloader.checksum(
                    new File(CommonRegister.GET_DECRYPTOR_PATH(DecryptorDiscr, PeerInfo.getOS())));

            String s2 = DecryptorDownloader.getChkSum(DecryptorDiscr);

            if(s1.equals(s2))
            {
                return true;
            }
            else
            {
                int response;
                response = JOptionPane.showConfirmDialog(null,"New Plugin didnt got verified." +
                        " This plugin may contain virus. Proceed anyway on ur won risk?");
                if(response == 0)
                    return true;
                else if(response == 1)
                {
                    gridPeer.setBlocked(true);
                    return false;
                }
                else
                    return false;
            }
        }
        catch(Exception ex)
        {
            try
            {   
                s.close();
            }
            catch(Exception e)
            {
                return false;
            }
            file.delete();
            return false;
        }
    }

    public static String getChkSum(String discr)
    {
        try {
            String urlstr = CommonRegister.SERVER_IP_PLUGIN_CHKSUM() + "?name=" + discr;
            URL url = new URL(urlstr);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            return br.readLine();
        } catch (Exception ex) {
            return null;
        }
    }

    public static String checksum(File file)
    {
      try
      {
          InputStream fin = new FileInputStream(file);

          java.security.MessageDigest md5er =
                  MessageDigest.getInstance("MD5");

          byte[] buffer = new byte[1024];

          int read;

          do
          {
              read = fin.read(buffer);
              if (read > 0)
                md5er.update(buffer, 0, read);
          } while (read != -1);

          fin.close();

          byte[] digest = md5er.digest();

          if (digest == null)
              return null;

          String strDigest = "0x";

          for (int i = 0; i < digest.length; i++)
          {
              strDigest += Integer.toString((digest[i] & 0xff)
                    + 0x100, 16).substring(1).toUpperCase();
          }

          return strDigest;
      }
      catch (Exception e)
      {
          return null;
      }
    }
}
