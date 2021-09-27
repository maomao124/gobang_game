import java.util.Scanner;

/**
 * Project name(项目名称)：五子棋游戏 gobang_game
 * Package(包名): PACKAGE_NAME
 * Author(作者）: mao
 * Author QQ：1296193245
 * Date(创建日期)： 2021/9/27
 * Time(创建时间)： 20:34
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Gobang
{
    // 定义棋盘的大小
    public static int BOARD_SIZE = 15;
    // 定义一个二维数组来充当棋盘
    public static String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
    String black = "●"; // 黑子
    String white = "○"; // 白子

    /**
     * 初始化械盘数组
     */
    public void initBoard()
    {
        // 把每个元素赋为"╋"，用于在控制台画出棋盘
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j] = "╋";
            }
        }
    }

    /**
     * 在控制台输出棋盘的方法
     */
    public void printBoard()
    {
        // 打印毎个数组元素
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                // 打印数组元素后不换行
                System.out.print(board[i][j]);
            }
            // 毎打印完一行数组元素后输出一个换行符
            System.out.print("\n");
        }
    }

    /**
     * 判断输赢的方法
     */
    public static boolean isWin(int x, int y, String color)
    {
        if (color.equals("black"))
        {
            color = "●";
        }
        if (color.equals("white"))
        {
            color = "○";
        }
        // 横向
        for (int i = 0; i < board.length - 5; i++)
        {
            if (board[x][i].equals(color) && board[x][i + 1].equals(color) && board[x][i + 2].equals(color)
                    && board[x][i + 3].equals(color) && board[x][i + 4].equals(color))
            {
                return true;
            }
        }
        // 竖向
        for (int i = 0; i < board.length - 5; i++)
        {
            if (board[i][y].equals(color) && board[i + 1][y].equals(color) && board[i + 2][y].equals(color)
                    && board[i + 3][y].equals(color) && board[i + 4][y].equals(color))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断指定坐标是否有棋子
     */
    public static boolean isOk(int x, int y)
    {
        if (!board[x - 1][y - 1].equals("╋"))
        {
            return false;
        }
        return true;
    }

    /**
     * 电脑下棋
     */
    public static String computer(String color)
    {
        int x = (int) (Math.random() * 14) + 1; // 生成一个1~14之间的随机数
        int y = (int) (Math.random() * 14) + 1; // 生成一个1~14之间的随机数
        // 判断电脑下棋的坐标有无棋子，有棋子则在重新生成随机数
        if (isOk(x, y))
        {
            if (color.equals("black"))
            {
                board[x][y] = "●";
            }
            else if (color.equals("white"))
            {
                board[x][y] = "○";
            }
        }
        else
        {
            computer(color);
        }
        return "x,y";
    }

    public static void main(String[] args) throws Exception
    {
        Gobang gb = new Gobang();
        gb.initBoard();
        gb.printBoard();
        // 这是用于获取键盘输入的方法
        Scanner input = new Scanner(System.in); // 使用Scanner类获取用户输入
        System.out.println("您想要选择什么颜色的棋，black或white，请输入：");
        String peopleColor = input.next(); // 定义用户选择棋子的颜色并返回用户输入的字符串
        // 如果用户选择的是白棋，则电脑先下（五子棋中黑棋先下）
        if (peopleColor.equals("white"))
        {
            System.out.println("您选择的是白棋");
            computer("black");
            gb.printBoard();
        }
        String inputStr;
        do
        {
            System.out.println("输入您下棋的坐标，应以x,y的格式：");
            inputStr = input.next();
            // 定义数组并赋值坐标x，,y
            String[] posStrArr = inputStr.split(",");
            int x = Integer.parseInt(posStrArr[0]);
            int y = Integer.parseInt(posStrArr[1]);
            // 如果输入坐标已有棋子，则重新输入坐标
            if (!isOk(x, y))
            {
                System.out.println("此处已有棋子，请换位置！");
                continue;
            }
            // 将上面分隔完以后的字符串转换成用户下棋的坐标
            int xPos = x;
            int yPos = y;
            // 定义电脑棋子颜色
            String comColor = null;
            // 根据用户选择的棋子颜色给对应的数组元素赋值
            if (peopleColor.equals("black"))
            {
                gb.board[xPos - 1][yPos - 1] = "●";
                comColor = "white";
            }
            else if (peopleColor.equals("white"))
            {
                gb.board[xPos - 1][yPos - 1] = "○";
                comColor = "black";
            }
            computer(comColor);
            gb.printBoard();
            // 判断输赢
            if (isWin(xPos - 1, yPos - 1, peopleColor))
            {
                System.out.println(peopleColor + "获胜！");
                break;
            }
            if (isWin(xPos - 1, yPos - 1, comColor))
            {
                System.out.println(comColor + "获胜！");
                break;
            }
        }
        while (inputStr != null);
    }
}
