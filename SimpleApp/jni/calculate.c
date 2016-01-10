
        #include<stdio.h>

#include <time.h>
#include <stdlib.h>

        int calculate(int min_num, int max_num)
        {
            int result=0,low_num=0,hi_num=0;
            if(min_num<max_num)
            {
                low_num=min_num;
                hi_num=max_num+1; // this is done to include max_num in output.
            }else{
                low_num=max_num+1;// this is done to include max_num in output.
                hi_num=min_num;
            }
            srand(time(0));
            result = (rand()%(hi_num-low_num))+low_num;
            return result;
        }
