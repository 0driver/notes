import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class test1
{
    public static void main (String[] args) throws java.lang.Exception
    {

        int[] arr = generateArray();
        int[] result=new int[arr.length];
//        mergesort1(arr,result,0,arr.length-1);
//        quicksort(arr,0,arr.length-1);
//        bubblesort(arr);
//        insertsort(arr);
//        selectsort(arr);
        sheelsort(arr);
        System.out.println("排序后"+Arrays.toString(arr));

    }

    /*
     *
     * 归并排序
     * */
    public static void mergesort1(int[] arr,int[] result,int left,int right){
        if(left>=right) return;

        int mid=(left+right)/2;
        int s1=left,e1=mid;
        int s2=mid+1,e2=right;

        mergesort1(arr,result,s1,e1);
        mergesort1(arr,result,s2,e2);

        int k=left;

        while(s1<=e1&&s2<=e2){
            if(arr[s1]<=arr[s2]){
                result[k++]=arr[s1++];
            }else{
                result[k++]=arr[s2++];
            }
        }
        while(s1<=e1){
            result[k++]=arr[s1++];
        }
        while(s2<=e2){
            result[k++]=arr[s2++];
        }
        for (int i = left; i <=right; i++) {
            arr[i]=result[i];
        }
    }

    /*
    *
    * 归并排序
    * */
    public static void mergesort2(int[] orignal,int[] result,int start,int end){
        if(start>=end) return ;

        int mid=(start+end)/2;
        int start1=start,end1=mid;
        int start2=mid+1,end2=end;

        mergesort2(orignal,result,start1,end1);
        mergesort2(orignal,result,start2,end2);

        int k=start;

        while(start1<=end1&&start2<=end2){
            if(orignal[start1]<=orignal[start2])
            {
                result[k++]=orignal[start1++];
            }
            else{
                result[k++]=orignal[start2++];
            }
        }

        while(start1<=end1){
            result[k++]=orignal[start1++];
        }

        while(start2<=end2){
            result[k++]=orignal[start2++];
        }
        for(int x=start;x<=end;x++){
            orignal[x]=result[x];
        }

    }

    /*
    *
    * 快速排序
    * */
    public  static void quicksort(int[] arr,int left,int right){
        if(left>=right) return;
        int basic=arr[left];
        int i=left;
        int j=right;
        while (i<j){
            while (i<j&&arr[j]>=basic){
                j--;
            }
            while (i<j&&arr[i]<=basic){
                i++;
            }
            if(i<j){
                swap(arr,i,j);
            }
        }
        swap(arr,left,i);
        quicksort(arr,left,i-1);
        quicksort(arr,i+1,right);

    }

    /*
    *
    * 冒泡排序
    * */
    public static void bubblesort(int[] arr){
        if(arr==null) return;
        for (int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
/*
*
* 插入排序
* */
    public static void insertsort(int[] arr){
        if(arr==null){return;}
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
    /*
    * 选择排序
    * */
    public static void selectsort(int[] arr){

        if(arr==null) return;

        for(int i=0;i<arr.length;i++){
            int min=i;
            for (int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            if(arr[min]!=arr[i]){
                swap(arr,min,i);
            }
        }

    }

    public static void sheelsort(int[] arr){

        for (int i=arr.length/2;i>0;i=i/2){
            for(int j=i;j<arr.length;j++){
                for (int k=j-i;k>=0;k=k-i){
                    if(arr[k]>arr[k+i]){
                        swap(arr,k,k+i);
                    }
                }
            }

        }

    }
    /*
    * 交换值
    * */
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
/*
* 随机生成一个数组 方便测试
* */
    public static int[] generateArray(){

        Random random=new Random();
        int i = random.nextInt(50);
        int[] arr=new int[i];

        for (int j=0;j<i;j++){
            int i1 = random.nextInt(20);
            arr[j]=i1;
        }

        System.out.println("排序前"+ Arrays.toString(arr));

        return arr;

    }
}