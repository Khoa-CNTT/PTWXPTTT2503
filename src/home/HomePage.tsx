import { Navigate } from "react-router-dom";
import { useAuthStore } from "../store/authStore";

const HomePage = () => {
  const { isAuthenticated } = useAuthStore();

  if (!isAuthenticated) {
    return <Navigate to={"/login"} />;
  }

  //return <HomeScreen />;
};

export default HomePage;
