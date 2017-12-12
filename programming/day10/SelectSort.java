/**
 * Interface for classes that due sorts
 */
public class SelectSort<T extends Comparable> implements ISort<T> {
    /**
     * Sorts an array of items in place
     * @param in An array to sort
     */
    public void sort(T[] in)
    {
      T[] newIn = (T[])new Comparable[in.length];
      for(int i=0; i<in.length; i++)
      {
        int minIdx=0;
        for(int j=0; j<in.length; j++)
        {
          if(in[j]!=null)
          {
            if(in[minIdx]==null)
            {
              minIdx=j;
            }
            if(in[j].compareTo(in[minIdx])<0)
            {
              minIdx=j;
            }
          }
        }
        newIn[i]=in[minIdx];
        //System.out.println(newIn[i]);
        in[minIdx]=null;
      }
      for(int i=0; i<in.length; i++)
      {
        in[i]=newIn[i];
      }

    }

    /**
     * Produces the name of the kind of sort implemented
     * @return the name of the sort algorithm
     */
    public String sortName()
    {
      return "SelectSort";
    }

    public static void main(String[] args)
    {
      ISort sort = new SelectSort<Integer>();
      Integer[] nums = {7,4,3,9,2,5,1,0,6,8};
      sort.sort(nums);
      for(int i=0; i<nums.length; i++)
      {
        System.out.println(nums[i]);
      }
    }
}
