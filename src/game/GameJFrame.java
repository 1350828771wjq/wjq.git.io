package game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //存储照片在数组中位置
    int[][] data = new int[4][4];

    //存储空白格在数组中的位置
    int x = 0;
    int y = 0;

    //正确数据
    int[][] win = new int[4][4];

    //步数
    int step = 0;

    //图片路径
    String path = "girl/girl2/";
    JMenu chosepicturejmenuitem = new JMenu("更换图片");
    JMenuItem newgamejmenuitem = new JMenuItem("重新游戏");
    JMenuItem newloginjmenuitem = new JMenuItem("重新登录");
    JMenuItem closegamejmenuitem = new JMenuItem("关闭游戏");
    JMenuItem aboutusjmenuitem = new JMenuItem("公众号");
    JMenu helpjumen = new JMenu("帮助");

    JMenuItem girls = new JMenuItem("美女");
    JMenuItem animals = new JMenuItem("动物");
    JMenuItem sports = new JMenuItem("运动");
    public GameJFrame() {
        //窗口
        initJframe();

        //初始化菜单
        initJmenubar();

        //打乱数据
        initData();

        //加入打乱的图片
        initImage();

        //界面显示
        this.setVisible(true);
    }

    private void initData() {
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        Random random = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = random.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    private void initImage() {
        //清空原有界面图片
        this.getContentPane().removeAll();

        //判断是否胜利
        if(isWin()){
            JLabel jlabel = new JLabel(new ImageIcon("win.png"));
            jlabel.setBounds(203, 308, 197, 73);
            this.getContentPane().add(jlabel);
        }

        //加载步数统计
        JLabel stepcount = new JLabel("步数: " + step + "步");
        stepcount.setBounds(50,30, 100, 20);
        this.getContentPane().add(stepcount);

        //加入图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建JLabel
                JLabel jlabel = new JLabel(new ImageIcon( path + num +".jpg"));
                //把JLabel加到JFrame
                jlabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //添加边框 1:突起 0：凹陷
                jlabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //添加到界面
                this.getContentPane().add(jlabel);
            }
        }
        //创建背景
        JLabel background = new JLabel(new ImageIcon("background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJmenubar() {
        JMenuBar jmenubar = new JMenuBar();
        JMenu functionjumen = new JMenu("功能");
        JMenu aboutjumen = new JMenu("关于我们");

        helpjumen.addActionListener(this);
        newgamejmenuitem.addActionListener(this);
        newloginjmenuitem.addActionListener(this);
        closegamejmenuitem.addActionListener(this);
        aboutusjmenuitem.addActionListener(this);
        chosepicturejmenuitem.addActionListener(this);

        girls.addActionListener(this);
        animals.addActionListener(this);
        sports.addActionListener(this);

        functionjumen.add(chosepicturejmenuitem);
        functionjumen.add(newgamejmenuitem);
        functionjumen.add(newloginjmenuitem);
        functionjumen.add(closegamejmenuitem);

        chosepicturejmenuitem.add(girls);
        chosepicturejmenuitem.add(animals);
        chosepicturejmenuitem.add(sports);

        aboutjumen.add(aboutusjmenuitem);

        jmenubar.add(functionjumen);
        jmenubar.add(aboutjumen);
        jmenubar.add(helpjumen);

        this.setJMenuBar(jmenubar);
    }

    private void initJframe() {
        this.setSize(603, 680);
        this.setTitle("单机版拼图游戏");
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中
        this.setLayout(null);
        //添加键盘监听
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按下不松
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //清空
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //加载背景
            JLabel background = new JLabel(new ImageIcon("background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            //刷新
            this.getContentPane().repaint();
        }
    }
    //松开
    @Override
    public void keyReleased(KeyEvent e) {
        //判断
        if (isWin()) {
            return;
        }
        int code = e.getKeyCode();
        if(code == 37){
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            //每走一步，步数加一
            step++;
            initImage();
        } else if (code == 38) {
            if(x == 3){
                return;
            }
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            //每走一步，步数加一
            step++;
            initImage();
        } else if (code == 39) {
            if(y == 0){
                return;
            }
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            //每走一步，步数加一
            step++;
            initImage();
        } else if (code == 40) {
            if(x == 0){
                return;
            }
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            //每走一步，步数加一
            step++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }
    //获胜判断
    public boolean isWin(){
        win = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == newgamejmenuitem){
            step = 0;
            initData();
            initImage();
        } else if (object == newloginjmenuitem) {
            this.setVisible(false);
            new Login();
        } else if (object == aboutusjmenuitem) {
            JDialog jdialog = new JDialog();
            JLabel jlabel = new JLabel(new ImageIcon("about.png"));
            jlabel.setBounds(0, 0, 258, 258);
            jdialog.getContentPane().add(jlabel);
            jdialog.setSize(344, 344);
            //居中
            jdialog.setLocationRelativeTo(null);
            //置顶
            jdialog.setAlwaysOnTop(true);
            //不关闭无法进行其他操作
            jdialog.setModal(true);
            //显示
            jdialog.setVisible(true);
        } else if (object == closegamejmenuitem) {
            System.exit(0);
        } else if (object == helpjumen) {
            JDialog jdialog = new JDialog();
            JLabel jlabel = new JLabel(new ImageIcon("damie.jpg"));
            jlabel.setBounds(0, 0, 99, 100);
            jdialog.getContentPane().add(jlabel);
            jdialog.setSize(244, 244);
            //居中
            jdialog.setLocationRelativeTo(null);
            //置顶
            jdialog.setAlwaysOnTop(true);
            //不关闭无法进行其他操作
            jdialog.setModal(true);
            //显示
            jdialog.setVisible(true);
        } else if (object == girls) {
            Random random = new Random();
            int num = random.nextInt(13);
            path = "girl/girl"+num+"/";
            step = 0;
            initData();
            initImage();
            this.getContentPane().repaint();
        } else if (object == animals) {
            Random random = new Random();
            int num = random.nextInt(8);
            path = "animal/animal"+num+"/";
            step = 0;
            initData();
            initImage();
            this.getContentPane().repaint();
        } else if (object == sports) {
            Random random = new Random();
            int num = random.nextInt(10);
            path = "sport/sport"+num+"/";
            step = 0;
            initData();
            initImage();
            this.getContentPane().repaint();
        }
    }
}

