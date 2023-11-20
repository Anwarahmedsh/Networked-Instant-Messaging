

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;

public class PublicVar {
    public static DataInputStream input;
    public static DataOutputStream output;
    public static String client;
    public static String message;
    
   static HashMap<String,PrivateChat> privateChatStore = new HashMap();
   static HashMap<String,PrivateChat> oldChatStore = new HashMap();
   static String CH;
    
}

