import java.util.Scanner;

public class Task1Main {
    //I suspect that this problem can be solved by a combinatorial formula,
    // but since I don't know the specific formula,
    // I solved this problem by recursively going through all valid options.
    // This solution may be slower than the solution through a specific formula,
    // but it is still a working solution.
    int count=0; //counter for all valid variants
    void recursive_bracket_building(int open,int closed,int n) //recursive function
    {   //the main idea is to keep track of closed and opened brackets.
        //opened brackets should always be <= than closed
        // to work with only valid variants and dont waste time on nonvalid
        if(open==n && closed==n) //checking if the variant is finished and incrementing counter if true
        {
            count++;
            return;
        }
        if(open<n) //add open bracket if able (cant add more opened brackets than N)
        {
            recursive_bracket_building(open+1,closed,n);
        }
        if(closed<open) //add closed bracket if able (cant add more closed brackets than opened)
        {
            recursive_bracket_building(open,closed+1,n);
        }
    }
    void main() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("N = ");
        int N=scanner.nextInt();
        recursive_bracket_building(0,0,N);
        System.out.println("Result: "+count+" valid variants");
    }
}
