package learnold;

public class Demo {
    public static void main(String[] args) {
        //ChromeOptions chromeOptions = new ChromeOptions();//работает ли при вебдрайвере манаджере

        //WebDriver driver = WebDriverManager.chromedriver().create();
        //driver.get("https://www.google.com");
        //2 4 5
        //3 4 7
        //1 2 9

        int abc[][] = {{2,4,5},{3,4,7},{1,2,9}};
        int min =abc[0][0];
        int mincolumn = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if(abc[i][j]<min){

                    min=abc[i][j];
                    mincolumn=j;
                }
            }
        }
        int max=abc[0][mincolumn];
        int k =0;
        while (k<3)
        {
            if(abc[k][mincolumn]>max){
                max = abc[k][mincolumn];
            }
            k++;
        }
        System.out.println(min);

//
//        ArrayList<Integer> ab = new ArrayList<Integer>();
//        for (int i = 0; i <a.length ; i++) {
//            int k =0;
//            if (!ab.contains(a[i])){
//
//            }
//        }








//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());
//        int a = 5;
//        int b = 6;
//        int c = a + b;
//        String bla = "some intresting text";
//        System.out.println(bla);
    }
}
