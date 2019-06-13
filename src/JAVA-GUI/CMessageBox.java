import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.*;
import javax.swing.JOptionPane;

public class CMessageBox extends TwoArgFunction{
    public LuaValue call(LuaValue modname, LuaValue env){
        //LuaValue library = tableOf();
        //library.set("MessageBox", new MessageBox);
        LuaValue MessageFunc = new cMessageBox();
        env.set("CMessageBox", MessageFunc);
        return MessageFunc;
    }
    static class cMessageBox extends OneArgFunction{
        public LuaValue call(LuaValue msg){
            JOptionPane.showMessageDialog(null,msg.toString());
            return LuaValue.valueOf(0);
        }
    }
}