package ingenias.jade.comm;

/**
 * It associates a Conversation id with the played role. An agent can play
 * different roles within the same conversation. Therefore, it is required
 * to distinguish the state behaviors referring to the conversations
 * associated to one role and others. This is achieved making the id of a
 * conversation the combination of the conversation id and the role played
 * 
 * @author jj
 * 
 */
public class Pair {
	String cid;
	String playedRole;

	public Pair(String cid, String playedRole) {
		this.cid = cid;
		this.playedRole = playedRole;
	}

	@Override
	public int hashCode() {
		return (cid.hashCode() + playedRole.hashCode()) % Integer.MAX_VALUE;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Pair) {
			return ((Pair) obj).cid.equals(cid)
			&& ((Pair) obj).playedRole.equals(playedRole);
		} else
			return super.equals(obj);
	}
}