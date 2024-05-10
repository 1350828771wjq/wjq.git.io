package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener, MouseListener {
    ArrayList<User> userArrayList = new ArrayList<>();
    //用户名
    {
        userArrayList.add(new User("kd", "020115"));
    }
    //登录按钮
    JButton loginJbutton = new JButton();
    //注册按钮
    JButton registerJbutton = new JButton();
    //添加用户名输入框
    JTextField userNameText = new JTextField();
    //添加密码输入框
    JPasswordField passwordText = new JPasswordField();
    //添加验证码输入框
    JTextField codeText = new JTextField();
    JLabel rightcode = new JLabel();
    public Login() {
        //设置登录窗口
        initLoginJframe();
        //设置登陆界面
        initLoginview();
        //显示出来
        this.setVisible(true);
    }

    //弹窗
    private void showJDialog(String str) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 100);
        jDialog.setAlwaysOnTop(true);
        //居中
        jDialog.setLocationRelativeTo(null);
        //不关闭无法继续
        jDialog.setModal(true);
        JLabel jLabel = new JLabel(str);
        jLabel.setBounds(0, 0, 200, 100);
        jDialog.add(jLabel);
        jDialog.setVisible(true);
    }

    //内容
    private void initLoginview() {
        //添加用户名
        JLabel userName = new JLabel(new ImageIcon("login/用户名.png"));
        userName.setBounds(116,135, 47, 17);
        this.add(userName);

        //添加用户名输入框
        userNameText.setBounds(180, 130, 150, 25);
        this.add(userNameText);

        //添加密码
        JLabel password = new JLabel(new ImageIcon("login/密码.png"));
        password.setBounds(123, 185, 47, 17);
        this.add(password);

        //添加密码输入框
        passwordText.setBounds(180, 180, 150, 25);
        this.add(passwordText);

        //添加验证码
        JLabel code = new JLabel(new ImageIcon("login/验证码.png"));
        code.setBounds(116, 235, 47, 17);
        this.add(code);

        codeText.setBounds(180, 230, 80, 25);
        this.add(codeText);

        //生成验证码
        String codeStr = CodeUtil.getCode();
        rightcode.setBounds(270, 230, 80, 25);
        rightcode.setText(codeStr);
        rightcode.addMouseListener(this);
        this.add(rightcode);

        //添加登录按钮
        loginJbutton.setBounds(116, 290, 100, 30);
        loginJbutton.setIcon(new ImageIcon("login/登录按钮.png"));
        //去除边框
        loginJbutton.setBorderPainted(false);
        //去除背景
        loginJbutton.setContentAreaFilled(false);
        this.getContentPane().add(loginJbutton);
        //loginJbutton.addActionListener(this);
        loginJbutton.addMouseListener(this);

        //添加注册按钮
        registerJbutton.setBounds(230, 290, 100, 30);
        registerJbutton.setIcon(new ImageIcon("login/注册按钮.png"));
        //去除边框
        registerJbutton.setBorderPainted(false);
        //去除背景
        registerJbutton.setContentAreaFilled(false);
        this.getContentPane().add(registerJbutton);
        //registerJbutton.addActionListener(this);
        registerJbutton.addMouseListener(this);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.add(background);
    }

    //窗体
    private void initLoginJframe() {
        this.setSize(485, 428);
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中
        this.setLayout(null);
        //this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*Object object = e.getSource();
        if(object == loginJbutton){
            //获取用户名和密码
            String userName = userNameText.getText();
            String password = passwordText.getText();
            //获取验证码
            String code = ((JTextField) this.getComponent(5)).getText();
            //判断验证码是否正确
            if(code.equals("codeStr")){
                //判断用户名和密码是否正确
                if(userName.equals("admin") && password.equals("123456")){
                    //登录成功
                    showJDialog("登录成功");
                }else{
                    //登录失败
                    showJDialog("用户名或密码错误");
                }
            }else{
                //验证码错误
                showJDialog("验证码错误");
            }
        }else if(object == registerJbutton){
            //注册
            new Register();
        }*/
    }

    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        Object object = e.getSource();
        if(object == loginJbutton){
            //获取用户名和密码
            String userNameinput = userNameText.getText();
            String passwordinput = passwordText.getText();
            User user = new User();
            //User user = new User(userNameinput, passwordinput);
            //获取验证码
            String codeinput = codeText.getText();
            if(codeinput.equals("")){
                showJDialog("验证码不能为空");
            } else if (userNameinput.equals("") || passwordinput.equals("")) {
                showJDialog("用户名或密码不能为空");
            }else {
                //判断验证码是否正确(忽略大小写）
                if (!codeinput.equalsIgnoreCase(rightcode.getText())) {
                    //验证码错误
                    showJDialog("验证码错误");
                }else if (userNameinput.equals(userArrayList.get(0).getUsername()) && passwordinput.equals(userArrayList.get(0).getPassword()))   {
                    //关闭当前登录界面
                    this.setVisible(false);
                    //打开游戏的主界面
                    //需要把当前登录的用户名传递给游戏界面
                    new GameJFrame();
                }else {
                    showJDialog("用户名或密码错误");
                }
            }
        }else if(object == registerJbutton){
            //注册
            new Register();
        } else if (object == rightcode) {
            //生成新的验证码
            String code = CodeUtil.getCode();
            rightcode.setText(code);
        }
    }

    //按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        Object object = e.getSource();
        if(object == loginJbutton){
            loginJbutton.setIcon(new ImageIcon("login/登录按下.png"));
        } else if (object == registerJbutton) {
            registerJbutton.setIcon(new ImageIcon("login/注册按下.png"));
        }
    }

    //按下松开
    @Override
    public void mouseReleased(MouseEvent e) {
        Object object = e.getSource();
        if(object == loginJbutton){
            loginJbutton.setIcon(new ImageIcon("login/登录按钮.png"));
        } else if (object == registerJbutton) {
            registerJbutton.setIcon(new ImageIcon("login/注册按钮.png"));
        }
    }

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
