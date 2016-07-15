import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Dragos on 7/15/2016.
 */
public class Lista {

    public static void main(String[] args){
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();

        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);

        l2.add(1);
        l2.add(2);
        l2.add(8);


        List<Integer> lr = l1.stream().filter(elem -> l2.contains(l1)).collect(Collectors.toList());
        Stream.concat(l1.stream(),l2.stream()).distinct().collect(Collectors.toList());
    }
}
