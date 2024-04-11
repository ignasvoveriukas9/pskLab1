package lt.vu.transactions;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
@SessionScoped
public class TransactionalComponentTwo implements Serializable {

    @Resource
    private TransactionSynchronizationRegistry tx;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String doSomething() {
        System.out.println("Component Two: " + tx.getTransactionKey());
        return "doSomethingFromComponentTwo";
    }

}
