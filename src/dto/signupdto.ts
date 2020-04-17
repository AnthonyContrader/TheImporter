import { UserDTO } from './userdto';

export class ManagedUserVM extends UserDTO{


    password: string;

    constructor(){
        super();
        this.password="";
    }
}
