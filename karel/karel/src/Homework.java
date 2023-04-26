import stanford.karel.SuperKarel;
public class Homework extends SuperKarel {
    public int col,row,steps;
    public void LeftThenRight(){turnLeft();putBeeper();move();steps++;putBeeper();turnRight();}
    public void RightThenLeft(){turnRight();putBeeper();move();steps++;putBeeper();turnLeft();}
    public void CountRowsAndColumns(){
        boolean completeCol = false, completeRow = false;
        while (!completeRow) {
            if (frontIsClear()) {
                move();
                if (!completeCol){ col++;steps++;}
                else {row++;steps++;}
            }
            else if (frontIsBlocked() && !completeCol){turnLeft();completeCol = true;}
            else if(frontIsBlocked() && !completeRow){turnLeft();completeRow = true;}
        }
    }
    public void OneColOrRow(int GreaterThanOne){
        if(GreaterThanOne==3){if(col==1)turnLeft();move();steps++;putBeeper();}
        if(GreaterThanOne==4){if(col==1)turnLeft();putBeeper();move();steps++;move();steps++;putBeeper();}
        if(GreaterThanOne==5 || GreaterThanOne==6){
            if(col==1)turnLeft();
            for(int i=0;i<5;i++){
                if(i%2==0) putBeeper();
                if(frontIsClear()) {move();steps++;}
            }
        }
        else if(GreaterThanOne>=7){
            if(col==1) turnLeft();
            int div=(GreaterThanOne-3)/4;
            int mod=(GreaterThanOne-3)%4;
            for(int i=0;i<3;i++){
                while(mod-->0){putBeeper();move();steps++;}
                for(int j=0;j<div;j++){move();steps++;}
                putBeeper();
                if(i<2){move();steps++;}
            }
        }
    }
    public void TwoColOrRow(int GreaterThanTwo) {
        if(GreaterThanTwo<=6){
            putBeeper();
            boolean block=false;
                for(int i=0;i<3;i++){
                    if((i%2==1 && col>2)||(i%2==0 && col==2)){if(col>2 ||(i>0 && col==2) )turnRight();move();steps++;turnLeft();move();steps++;}
                    else {turnLeft();move();steps++;turnRight();move();steps++;}
                    putBeeper();
                    if(frontIsBlocked()){block=true;break;}
                }
                if(!block){
                    for(int i=0;i<4 && frontIsClear();i++) {
                        move();steps++;putBeeper();
                        if(col==2){
                            if(i==0 || i==3) turnLeft();
                            else turnRight();
                        }
                        else{
                            if(i==0 || i==3) turnRight();
                            else {turnLeft();}
                        }
                    }
                }
            }
        else{
            int div=(GreaterThanTwo-3)/4;
            int mod=(GreaterThanTwo-3)%4;
            if(mod==0 && col==2){move();steps++;turnLeft();}
            int EvenOrOdd=mod;
            for(int j=0;j<mod;j++){
                if ((j%2==1 && col>2)||(j % 2 == 0 && col==2)) {if(col>2 ||(j>0 && col==2))turnRight();putBeeper();move();steps++;putBeeper();turnLeft();}
                else{LeftThenRight();}
                move();steps++;
            }
            for(int i=0;i<3;i++){
                if(i>0) for(int j=0;j<=div;j++){move();steps++;}
                else for(int j=0;j<div;j++){move();steps++;}
                if(col==2){
                    if(mod==0) {
                        if (EvenOrOdd % 2 == 0) {LeftThenRight();}
                        else {RightThenLeft();}
                    }
                    else{
                        if (EvenOrOdd % 2 == 0) {RightThenLeft();}
                        else {LeftThenRight();}
                    }
                }
                else {
                    if (EvenOrOdd % 2 == 0) {LeftThenRight();}
                    else {RightThenLeft();}
                }
                EvenOrOdd--;
            }
        }
    }
    public void ThreeOrMore() {
        if(col%2==1 && row%2==1) {
            int low=Math.min((col/2),(row/2));
            int high=Math.max((col/2),(row/2));
            if(col<=row){
                for (int i = 0; i < low ; i++) {move();steps++;}
                turnLeft();
            }
            else{
                turnLeft();
                for(int i=0;i<low;i++){move();steps++;}
                turnRight();
            }
            for (int i = 0; i < high ; i++) {putBeeper();move();steps++;}
            turnLeft();
            for (int i = 0; i < low ; i++) {move();steps++;}
            turnAround();
            if(col<=row){for(int i=0;i<col-1;i++){putBeeper();move();steps++;}}
            else{for(int i=0;i<row-1;i++){putBeeper();move();steps++;}}
            putBeeper();turnAround();
            for(int i=0;i<low;i++){move();steps++;}
            turnRight();
            for (int i = 0; i < high ; i++) {move();steps++;putBeeper();}
        }
        else if((col%2==0 && row%2==1) || (col%2==1 && row%2==0)) {
            int Odd=col,Even=row;
            if(col%2==0){ Odd=row;Even=col;}
            int Moves=Odd/4;
            if(col%2==1){
                for (int i = 0; i < Odd/2 ; i++) {move();steps++;}
                turnLeft();
            }
            else{
                turnLeft();
                for (int i = 0; i < Odd/2 ; i++) {move();steps++;}
                turnRight();
            }
            for (int i = 0; i < Even/2 ; i++) {putBeeper();move();steps++;}
            turnLeft();
            for (int i = 0; i < Odd/2 ; i++) {move();steps++;}
            turnAround();
            if((Odd/2)%2==0){Moves--;}
            for(int i=0;i<Moves;i++){putBeeper();move();steps++;}
            if((Odd/2)%2==1){RightThenLeft();}
            else{turnRight();putBeeper();move();steps++;turnLeft();}
            if((Odd/2)%2==0){Moves++;}
            for(int i=0;i<Moves;i++){move();steps++;putBeeper();}
            move();steps++;move();steps++;
            if((Odd/2)%2==0){Moves--;}
            for(int i=0;i<Moves;i++){putBeeper();move();steps++;}
            if((Odd/2)%2==1){LeftThenRight();}
            else{turnLeft();putBeeper();move();steps++;turnRight();}
            if((Odd/2)%2==0){Moves++;}
            for(int i=0;i<Moves;i++){move();steps++;putBeeper();}
            turnAround();
            for (int i = 0; i < Odd/2 ; i++) {move();steps++;}
            turnRight();
            for (int i = 0; i < Even/2 ; i++) {putBeeper();if(i<Even/2-1)move();steps++;}
        }
        else if(col%2==0 && row%2==0){
            int FirstArea=(row/2)*((col/2)-1);
            int SecondArea=(col/2)*((row/2)-1);
            int high=col,low=row;
            if(col<row){
                FirstArea=(col/2)*((row/2)-1);
                SecondArea=(row/2)*((col/2)-1);
                high=row;low=col;
                turnLeft();
                for(int i=0;i<high/2;i++){move();steps++;}
                turnRight();
            }
            else{
                for(int i=0;i<high/2-1;i++){move();steps++;}
                turnLeft();
            }
            int dif=Math.abs(FirstArea-SecondArea);
            int Moves=(high/2)-(dif/2);
            for(int i=0;i<low/2-1;i++){putBeeper();move();steps++;}
            turnRight();
            for(int i=0;i<Moves;i++){putBeeper();move();steps++;}
            putBeeper();turnLeft();
            if(dif%2==1){move();steps++;putBeeper();turnRight();}
            else{move();steps++;turnRight();}
            for(int i=0;i<dif/2;i++){move();steps++;putBeeper();}
            turnAround();
            for(int i=0;i<high/2-1;i++){move();steps++;}
            for(int i=0;i<Moves;i++){putBeeper();move();steps++;}
            putBeeper();turnLeft();
            if(dif%2==1){move();steps++;putBeeper();turnRight();}
            else{move();steps++;turnRight();}
            for(int i=0;i<dif/2;i++){move();steps++;putBeeper();}
            turnAround();
            for(int i=0;i<high/2;i++){move();steps++;}
            turnLeft();move();steps++;move();steps++;
            for(int i=0;i<low/2-1;i++){putBeeper();if(i<(low/2-1)-1)move();steps++;}
        }
    }
    public void run() {
        col = 1; row = 1;steps=0;
        CountRowsAndColumns();
        if(col==1 || row==1){
            int Max=col;
            if(row>1) Max=row;
            OneColOrRow(Max);
            }
        else if(col==2 || row == 2){
            int Max=col;
            if(col>2)  Max=col;
            else if(row>2) Max=row;
            TwoColOrRow(Max);
        }
        else {
            ThreeOrMore();
        }
        System.out.println("Number of Rows : "+row);
        System.out.println("Number of Columns : "+col);
        System.out.println("Number of Steps : "+steps);
    }
}