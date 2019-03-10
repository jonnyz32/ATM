
public class Pair<L,R> {

    private final L left;
    private final R right;

    Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    L getLeft() { return left; }
    R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair other = (Pair) o;
        return this.left.equals(other.getLeft()) &&
                this.right.equals(other.getRight());
    }

}