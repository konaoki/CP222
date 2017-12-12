/**
* Interface for classes that due sorts
*/
public class MergeSort<T extends Comparable> implements ISort<T> {
  /**
  * Sorts an array of items in place
  * @param in An array to sort
  */
  public void sort(T[] in)
  {
    T[] leftHalf;
    T[] rightHalf;
    if(in.length!=1)
    {
      leftHalf = (T[])new Comparable[(int)in.length/2];
      rightHalf = (T[])new Comparable[in.length-leftHalf.length];
      for(int i=0; i<in.length; i++)
      {
        if(i<leftHalf.length)
        {
          leftHalf[i]=in[i];
        }
        else
        {
          rightHalf[i-leftHalf.length]=in[i];
        }
      }
      sort(leftHalf); sort(rightHalf);
      int leftCount=0; int rightCount=0;
      for(int i=0; i<in.length; i++)
      {
        //System.out.println("lcount: "+leftCount+" leftlength: "+leftHalf.length);
        //System.out.println("rcount: "+rightCount+" rightlength: "+rightHalf.length);
        if(leftCount==leftHalf.length)
        {

          in[i]=rightHalf[rightCount];
          rightCount++;
        }
        else if(rightCount==rightHalf.length)
        {
          in[i]=leftHalf[leftCount];
          leftCount++;
        }
        else if(leftHalf[leftCount].compareTo(rightHalf[rightCount])<0)
        {
          in[i]=leftHalf[leftCount];
          if(leftCount<leftHalf.length)
          {
            leftCount++;
          }
        }
        else
        {
          in[i]=rightHalf[rightCount];
          if(rightCount<rightHalf.length)
          {
            rightCount++;
          }
        }
      }
    }
    else
    {
      return;
    }
  }

  /**
  * Produces the name of the kind of sort implemented
  * @return the name of the sort algorithm
  */
  public String sortName()
  {
    return "MergeSort";
  }

  public static void main(String[] args)
  {
    ISort sort = new MergeSort<Integer>();
    Integer[] nums = {7,4,3,9,2,5,1,0,6,8};
    sort.sort(nums);
    for(int i=0; i<nums.length; i++)
    {
      System.out.println(nums[i]);
    }
  }
}
