package lt.vu.transactions;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
@SessionScoped
public class TransactionalComponentOne implements Serializable {

    @Resource
    private TransactionSynchronizationRegistry tx;

    @Inject
    private TransactionalComponentTwo transactionalComponentTwo;

    @Transactional
    public String doSomething() {
        System.out.println("Component One: " + tx.getTransactionKey());
        return transactionalComponentTwo.doSomething();
    }
}
