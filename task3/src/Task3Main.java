public class Task3Main {
    void main() {
        //this problem can be solved using BigInteger lib
        //but assuming this task is made to show my solving skills i present this algorithm
        //The main idea to deal with numbers that exceeds data type limits is "Divide and conquer"
        // which means to not store the whole big number in one place but to divide it to small managable pieces
        // and operate with them
        int []digits = new int[200]; //representation of big number in array of it's digits
        digits[0]=1; //setting first number to work with
        // (if dont, then code duplication will be present to prevent continuous multiplying of 0)
        int size=1; //initial size of result number
        for(int multiplier=2;multiplier<=100;multiplier++) //loop to multiply result number by 2..100
        {//the idea is to program column multiplication (multiply 1 digit at a time and dealing with carry number)
            int carry=0;
            for(int i=0;i<size;i++)
            {
                int result = digits[i]*multiplier+carry;
                digits[i]=result%10;
                carry=result/10;
            }
            while(carry>0) //dealing with carry number
            {
                size++;
                digits[size-1]=carry%10;
                carry/=10;
            }
        }
        int sum=0;
        for(int i=0;i<size;i++) //loop to count the result of digits sum
        {
            sum+=digits[i];
        }
        System.out.printf("Result (sum of digits of 100!): "+sum);
    }
}
