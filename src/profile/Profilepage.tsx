import { useEffect, useState } from "react";
import { useUsersStore } from "../../store/usersStore";
import { usePostStore } from "../../store/postStore";
import { PostType } from "../../types/post.type";
import { Posts } from "../../components/Posts";

export const Profilepage = () => {
  const [posts, setPosts] = useState<PostType[]>([]);

  const { getMe } = useUsersStore();
  const { getPostsByUserName } = usePostStore();

  useEffect(() => {
    const fetchDatas = async () => {
      const data = await getMe();
      const posts = await getPostsByUserName(data.username, 1, 10);
      setPosts(posts);
    };

    fetchDatas();
  }, []);

  return <Posts posts={posts} setPosts={setPosts} />;
};
