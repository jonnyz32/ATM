
public class AccountRequest<L,M,R> {

    private final L user;
    private final M type;
    private final R name;

    AccountRequest(L user, M type, R name) {
        this.user = user;
        this.type = type;
        this.name = name;
    }

    L getUser() { return user; }
    M getType() { return type; }
    R getName() { return name; }

    @Override
    public int hashCode() { return user.hashCode() ^ type.hashCode() ^ name.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AccountRequest)) return false;
        AccountRequest other = (AccountRequest) o;
        return this.user.equals(other.getUser()) &&
                this.type.equals(other.getType()) &&
                this.name.equals(other.getName());
    }

}