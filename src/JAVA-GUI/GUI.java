import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

public class GUI{
    public static void main(String[] args){
        cal c = new cal();
        c.main();
    }
}

class cal {
    final LuaValue _G = JsePlatform.standardGlobals();

    JFrame demo = new JFrame("計算機");

    // JButton exitBtn = new JButton("Exit");
    JPanel panel = new JPanel(new FlowLayout());
    JLabel display = new JLabel("顯示器", SwingConstants.RIGHT);
    JButton[] number = {
        new JButton("1"),
        new JButton("2"),
        new JButton("3"),
        new JButton("4"),
        new JButton("5"),
        new JButton("6"),
        new JButton("7"),
        new JButton("8"),
        new JButton("9"),
        new JButton("0")
    };

    JButton eqBtn   = new JButton("=");
    JButton cBtn    = new JButton("C");
    JButton acBtn   = new JButton("AC");
    JButton addBtn  = new JButton("+");
    JButton minBtn  = new JButton("-");
    JButton divBtn  = new JButton("/");
    JButton douBtn  = new JButton("*");
    JButton backBtn = new JButton("←");
    JButton[] opBtn = {addBtn, minBtn, divBtn, douBtn};




    public void main() {
/*Loading lua evn*/
        _G.get("require").call(LuaValue.valueOf("cal"));
        demo.setSize(400, 300);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout GBLayout = new GridBagLayout();
        panel.setLayout(new GridBagLayout());
        /* Event */
        // exitBtn.addActionListener(new ActionListener(){
        //         public void actionPerformed(ActionEvent e)
        //             {
        //                 System.exit(0);
        //             }
        //     });

        for(JButton btn:number){
            final String num = btn.getText();
            btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        _G.get("on_number_click").call(LuaValue.valueOf(num));
                        updateScreen();
                    }
                });
        }

        for(JButton btn:opBtn){
            final String op = btn.getText();
            btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        _G.get("on_op_click").call(LuaValue.valueOf(op));
                        updateScreen();
                    }
                });
        }

        eqBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    _G.get("on_EQ_click").call();
                    updateScreen();
                }
            });

        acBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    _G.get("on_AC_click").call();
                    updateScreen();
                }
            });

        cBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    _G.get("on_C_click").call();
                    updateScreen();
                }
            });

        backBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    _G.get("on_back_click").call();
                    //JOptionPane.showMessageDialog(null, "test");
                    updateScreen();
                }
            });
        /* Add to Frame */
        // This just for develope
        demo.getContentPane().add(BorderLayout.NORTH, display);
        //demo.getContentPane().add(BorderLayout.NORTH, exitBtn);
        demo.add(panel);
        int x = 0,y = 1;
        for(JButton btn:number){
            int width = 1,height = 1;
            if (btn.getText().equals("0")){
                x++;
            }
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = x;
            c.gridy = y;
            c.gridwidth = width;
            c.gridheight = height;
            c.weightx = 0.003;
            c.weighty = 0.003;
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            panel.add(btn, c);
            x++;
            if (x%3 == 0){
                x = 0;
                y++;
            }
        }
        x = 4;
        y = 1;
        for(JButton btn:opBtn){
            int width = 1,height = 1;
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = x;
            c.gridy = y;
            c.gridwidth = width;
            c.gridheight = height;
            c.weightx = 0.003;
            c.weighty = 0.003;
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            panel.add(btn, c);
            y++;
        }
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.003;
        c.weighty = 0.003;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(eqBtn,c);
        c.gridx = 3;
        c.gridy = 0;
        panel.add(cBtn,c);
        c.gridx = 4;
        c.gridy = 0;
        panel.add(acBtn,c);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(backBtn,c);

        //
        demo.setVisible(true);
    }

    void updateScreen(){
        LuaValue result = _G.get("input_number");
        display.setText(result.toString());
    }
}
