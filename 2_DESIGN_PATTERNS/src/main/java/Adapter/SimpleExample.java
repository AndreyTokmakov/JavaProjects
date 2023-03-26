package Adapter;

public class SimpleExample
{
    interface OldApi {
        void callOldApi();
    }

    interface ModernApi {
        void call();
    }

    static class OldFashionObject implements OldApi {
        @Override
        public void callOldApi() {
            System.out.println("OldFashionObject::callOldApi()");
        }
    }

    static class ModernStyleObject implements ModernApi {
        @Override
        public void call() {
            System.out.println("ModernStyleObject::ModernApi()");
        }
    }

    static class ApiAdapter implements OldApi, ModernApi {

        private final OldApi oldApiObject;

        ApiAdapter(OldApi oldApiObject) {
            this.oldApiObject = oldApiObject;
        }

        @Override
        @Deprecated(since = "1.1.1")
        public void callOldApi() {
            System.out.println("Please do not use old API anymore");
        }

        @Override
        public void call() {
            System.out.print("Some new logic: ");
            oldApiObject.callOldApi();
        }
    }

    static class Client {

        public static void useOldApi(OldApi obj) {
            obj.callOldApi();
        }

        public static void useModernApi(ModernApi obj) {
            obj.call();
        }
    }

    public static void main(String[] args)
    {
        OldApi oldObj = new OldFashionObject();
        ModernApi modernObj = new ModernStyleObject();

        Client.useOldApi(oldObj);
        Client.useModernApi(modernObj);

        System.out.println("-".repeat(120));

        ModernApi adapter = new ApiAdapter(oldObj);

        Client.useOldApi(oldObj);
        Client.useModernApi(adapter);

    }
}
