public class test1 {
    public static void main(String[] args) throws java.lang.Exception {

        int[] arr = generateArray();
        int[] result = new int[arr.length];
//        mergesort1(arr,result,0,arr.length-1);
//        quicksort(arr,0,arr.length-1);
//        bubblesort(arr);
//        insertsort(arr);
//        selectsort(arr);
//        sheelsort(arr);

        HeapSort(arr, arr.length);
        System.out.println("排序后" + Arrays.toString(arr));

    }

    /*
     *
     * 归并排序
     * */
    public static void mergesort1(int[] arr, int[] result, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;//找到中间元素
        int s1 = left, e1 = mid;
        int s2 = mid + 1, e2 = right;

        mergesort1(arr, result, s1, e1);//对前面的数据排序
        mergesort1(arr, result, s2, e2);//对后面的数据排序

        int k = left;//用来指向结果数组的指针

//        不停的比较前半部分数组和后半部分数组的大小，然后存入结果数组
        while (s1 <= e1 && s2 <= e2) {
            if (arr[s1] <= arr[s2]) {
                result[k++] = arr[s1++];
            } else {
                result[k++] = arr[s2++];
            }
        }
//        当前半部分数组还没有存完，后半部分存完了，则将前半部分剩下的存入结果数组
        while (s1 <= e1) {
            result[k++] = arr[s1++];
        }
//        当后半部分数组还没有存完，前半部分存完了，则将后半部分剩下的存入结果数组
        while (s2 <= e2) {
            result[k++] = arr[s2++];
        }
        //最后将有序的结果数组重新赋值给原数组。
        for (int i = left; i <= right; i++) {
            arr[i] = result[i];
        }
    }

    /*
     *
     * 归并排序
     * */
    public static void mergesort2(int[] orignal, int[] result, int start, int end) {
        if (start >= end) return;

        int mid = (start + end) / 2;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;

        mergesort2(orignal, result, start1, end1);
        mergesort2(orignal, result, start2, end2);

        int k = start;

        while (start1 <= end1 && start2 <= end2) {
            if (orignal[start1] <= orignal[start2]) {
                result[k++] = orignal[start1++];
            } else {
                result[k++] = orignal[start2++];
            }
        }

        while (start1 <= end1) {
            result[k++] = orignal[start1++];
        }

        while (start2 <= end2) {
            result[k++] = orignal[start2++];
        }
        for (int x = start; x <= end; x++) {
            orignal[x] = result[x];
        }

    }

    /*
     *
     * 快速排序
     * */
    public static void quicksort(int[] arr, int left, int right) {

        if (left >= right) return;
        //选择数组的第一个元素作为基准数，大于等于该数字的数全部在其右边，小于等于的则全部在左边
        int basic = arr[left];
        int i = left;
        int j = right;
//        当前指针等于右指针的时候，退出循环
        while (i < j) {
//            当右指针遇到小于基准数的数则停下来
            //这里一定要从右指针开始，因为第一个数字是基准数，当左指针一直没有移动，右指针与其相撞，能确定相撞的数就是基准数本身，
            // 后面交换基准数和中间数的时候就不会出错。
//            否则从左边出发，在最右边相撞的时候，无法确定这个数是否大于基准数还是小于基准数，如果大于，则不符合逻辑，中间数右边的数，小于基准数。
            while (i < j && arr[j] >= basic) {
                j--;
            }
            while (i < j && arr[i] <= basic) {
                i++;
            }
//            相等时，相撞了，会退出循环，需要在循环外边将基准数和中间交换
            if (i < j) {
                swap(arr, i, j);
            }
        }
        //交换中间数和基准数，相撞时，指向的数一定是小于等于基准数的。
        swap(arr, left, i);
        quicksort(arr, left, i - 1);
        quicksort(arr, i + 1, right);

    }

    /*
     *
     * 冒泡排序
     * */
    public static void bubblesort(int[] arr) {
        if (arr == null) return;
        //冒牌排序要比较  *数组的长度-1*  次
        for (int i = 0; i < arr.length - 1; i++) {
//            每一次要将最大或者最小的元素移动到最右边，所以要移动数组的  *长度-1-已经排序过个数*
            for (int j = 0; j < arr.length - i - 1; j++) {
                //两两交换，此处是大于，表示升序排列，小于则是降序
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /*
     *
     * 插入排序
     * */
    public static void insertsort(int[] arr) {
        if (arr == null) {
            return;
        }
        /*
        * 插入排序要维护一个已经排好序列的有序数组，和一个待排序的数组。
        * 此处假设0号元素是有序数组。从有序数组右边的第一个元素开始，和其比较。
        * */
        for (int i = 1; i < arr.length; i++) {
//            让右边第一个元素插入到有序队列中
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /*
     * 选择排序
     * */
    public static void selectsort(int[] arr) {

        if (arr == null) return;

        for (int i = 0; i < arr.length; i++) {
            //每一次要从数组中选出最小（最大）的数字所在下标。这里初始化认为数组的第一个元素为最小值所在下标。
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
//                寻找最小数所在下标
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
//            交换
            if (arr[min] != arr[i]) {
                swap(arr, min, i);
            }
        }

    }

    public static void sheelsort(int[] arr) {
        //增量
        for (int i = arr.length / 2; i > 0; i = i / 2) {
            //直接插入排序
            for (int j = i; j < arr.length; j++) {
                for (int k = j - i; k >= 0; k = k - i) {
                    if (arr[k] > arr[k + i]) {
                        swap(arr, k, k + i);
                    }
                }
            }

        }

    }

    //堆的维护
    public static void MaxHeap(int[] arr, int index, int len) {
		
		//父节点i的左子节点在位置{\displaystyle (2i+1)}{\displaystyle (2i+1)};
		//父节点i的右子节点在位置{\displaystyle (2i+2)}{\displaystyle (2i+2)};
		//子节点i的父节点在位置{\displaystyle floor((i-1)/2)}{\displaystyle floor((i-1)/2)};
        int largest = index;
        int leftSon = (2 * index) + 1;
        int rightSon = (2 * index) + 2;
//注意，下标的判断一定要卸载值判断的前面，否则由于逻辑表达式的短路特性，会出现数组越界。
        if (leftSon < len && arr[leftSon] > arr[largest]) {
            largest = leftSon;
        }
        if (rightSon < len && arr[rightSon] > arr[largest]) {
            largest = rightSon;
        }
        if (largest != index) {
            swap(arr, index, largest);
            MaxHeap(arr, largest, len);
        }
    }

    //堆排序入口
    public static void HeapSort(int[] arr, int len) {

        /*
        首先要将一个无序的堆（数组存储）构建成一个有序的堆。从最后一个节点的父节点开始调整
       为什么要从这个节点开始，因为堆维护的过程是从上往下的，如果从前面开始，
       如果后面出现大于祖父节点数，那么将无法调整。
       */
//        构建最大堆的过程就是最大元素上浮。
        for (int i = (len-1)/2; i >= 0; i--) {
            MaxHeap(arr, i, len);
        }
//        将堆顶元素和最后的一个元素交换位置（取走堆顶元素），并调整最大堆（将堆顶元素下沉）。
        for (int j = len ; j >= 0; j--) {
            swap(arr, 0, j);
            MaxHeap(arr, 0, j);
        }

    }

    /*
     * 交换值
     * */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * 随机生成一个数组 方便测试
     * */
    public static int[] generateArray() {

        Random random = new Random();
        int i = random.nextInt(50);
        int[] arr = new int[i];

        for (int j = 0; j < i; j++) {
            int i1 = random.nextInt(20);
            arr[j] = i1;
        }

        System.out.println("排序前" + Arrays.toString(arr));

        return arr;

    }


}